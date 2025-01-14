package com.example.libraryapp

import android.content.Context
import android.util.Log
import com.example.libraryapp.model.AccountPost
import com.example.libraryapp.model.Book
import com.example.libraryapp.model.Collection
import com.example.libraryapp.model.SocialPost
import com.example.libraryapp.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseSeeder {
    suspend fun seed(
        context: Context,
        bookDao: BookDao,
        collectionDao: CollectionDao,
        socialPostDao: SocialPostDao,
        userProfileDao: UserProfileDao
    ) {
        withContext(Dispatchers.IO) {
            if (bookDao.getAllBooks().isEmpty()) {
                seedBooks(bookDao)
            }
            if (collectionDao.getAllCollections().isEmpty()){
                seedCollections(collectionDao)
            }
            if (socialPostDao.getAllSocialPosts().isEmpty()){
                seedSocialPosts(socialPostDao)
            }
            if (userProfileDao.getUserProfile() == null){
                seedUserProfile(userProfileDao)
            }
        }
    }

    private suspend fun seedBooks(bookDao: BookDao) {
        try {
            val books = listOf(
                Book(id = 1, title = "Laskar Pelangi", author = "Andrea Hirata", year = 2005, synopsis = "Menceritakan tentang kehidupan 10 anak dari keluarga miskin yang bersekolah di SD dan SMP Muhammadiyah di Desa Gantung, Belitung.", coverPath = "https://drive.google.com/uc?id=1MSbkJWG5lmySBI4xDONicQaPBXWTuw04", queueCount = 12, availableCount = 1),
                Book(id = 2, title = "Membuat Game Augmented Reality (AR) dengan Unity 3D", author = "Ir. Ulfah Mediaty Arief, M.T. dkk.", year = 2019, synopsis = "Buku ini akan menjadi referensi yang baik untuk mahasiswa Jurusan Teknik Informatika, Ilmu Komputer, dan sejenisnya. Selain itu, buku ini juga dapat menjadi referensi bagi pecinta game yang ingin mencoba membuat game sendiri.", coverPath = "https://drive.google.com/uc?id=1f_79qZFyhGK9QynB5HNGUy70hCNU0OAk", queueCount = 0, availableCount = 19),
                Book(id = 3, title = "Bandit Bandit Berkelas", author = "Tere Liye", year = 2024, synopsis = "Mereka memang adalah bedebah.\nBandit-bandit.\nTapi mereka bukan pengkhianat, orang-orang bermuka dua, penjilat dan tabiat murahan lainnya.\nMereka adalah bandit-bandit dengan kehormatan. Setia kawan. Pemegang janji terbaik.\nMereka adalah bandit-bandit berkelas.", coverPath = "https://drive.google.com/uc?id=1HFs2o0nm4WsbNqUT7GG0KtmCoRXojdpy", queueCount = 21, availableCount = 11),
                Book(id = 4, title = "Sebuah Usaha Melupakan", author = "Boy Candra", year = 2016, synopsis = "Buku ini bercerita tentang “Aku” yang sangat mencintai pasangannya, dia awalnya memuji dan mengelukan pasangannya dengan kata-kata manis, pujian, dan cinta, meski mereka berhubungan jarak jauh. Hubungan mereka manis, penuh janji dan harapan. Namun, ketika si “aku” dihianati oleh pasangannya akibat kehadiran orang ketiga, dia beralih menjadi pembenci, dan berkata kasar. “aku” yang tadinya sangat romantis, manis, penuh kata-kata pujian tentang bagaimana bahagianya dia menemukan pasangannya, beralih mengeluarkan kata-kata yang kasar, seperti “Kau bukan orang yang layak diperjuangkan sepenuh hati” atau “Waktu akan mengutukmu, hingga tak ada satu hal pun yang menjadi bahagia yang bersedia mengetuk dadamu”.", coverPath = "https://drive.google.com/uc?id=1GHfw-A7LV1w8J3ywnrew1LBMw8YM_A5F", queueCount = 27, availableCount = 13),
                Book(id = 5, title = "Sejarah Filsafat Barat", author = "Stephen Evans, C.", year=2024, synopsis = "Memuat tokoh-tokoh utama dan pandangan-pandangan dari filsafat-filsafat besar yang membentuk sejarah pemikiran manusia, yang ditulis dengan sangat jelas dan ringkas.", coverPath = "https://drive.google.com/uc?id=1nHJVxq_W2nltaq2_aCAdOuKqyRin6SG_", queueCount = 1, availableCount = 1),
                Book(id = 6, title = "B.J. Habibie", author = "R. William Liddle", year = 1998, synopsis = "Menganalisis peran B.J. Habibie dalam transisi politik Indonesia.", coverPath = "https://drive.google.com/uc?id=1gj-JIO-u43oDV6AAPPqlC48NLVCbw1T3", queueCount = 5, availableCount = 3),
                Book(id = 7, title = "Buya Hamka", author = "EmhaF", year = 2021, synopsis = "Membahas pemikiran dan gagasan Buya Hamka.", coverPath = "https://drive.google.com/uc?id=1zjFzRypnbEkFmVVRsK_nWeIBUreL-vFa/view?usp=drive_link", queueCount = 15, availableCount = 2),
                Book(id = 8, title = "Teori dan Praktik Desain UI/UX", author = "Rifda Faticha Alfa Aziza", year = 2023, synopsis = "Panduan lengkap tentang desain antarmuka dan pengalaman pengguna.", coverPath = "https://drive.google.com/uc?id=1X_obTam9MOKiiAMpNE6uB46WwUEQl5FG", queueCount = 8, availableCount = 1),
                Book(id = 9, title = "Dinamika Regulasi Ketenagakerjaan", author = "Dr. Haiyani Rumondang, M.A.", year = 2023, synopsis = "Analisis mendalam tentang perubahan regulasi ketenagakerjaan.", coverPath = "https://drive.google.com/uc?id=1X188pTgL7Mq59hWZ2oCJIR1w97wLOFxE", queueCount = 3, availableCount = 5),
                Book(id = 10, title = "Filsafat Berfikir", author = "EmhaF", year = 2022, synopsis = "Mengupas konsep dan pentingnya berfikir filosofis.", coverPath = "https://drive.google.com/uc?id=1Pc2FUWmwyvBqM1F5ZEBQv9_R-5Y6VFlG", queueCount = 10, availableCount = 4),
                Book(id = 11, title = "Habis Gelap Terbitlah Terang", author = "R.A. Kartini", year = 1911, synopsis = "Kumpulan surat-surat Kartini tentang emansipasi wanita.", coverPath = "https://drive.google.com/uc?id=1uYhAdLBR9RPN2_j-2GyQ3WH0bqkDOcGm", queueCount = 20, availableCount = 1),
                Book(id = 12, title = "Money Genius", author = "Rana Kinasih", year = 2023, synopsis = "Panduan praktis untuk mengelola keuangan dengan cerdas.", coverPath = "https://drive.google.com/uc?id=12OhzSoImQZrZdBE14R-motKmMMn56sAd", queueCount = 7, availableCount = 2),
                Book(id = 13, title = "Trauma", author = "Boy Candra", year = 2023, synopsis = "Kumpulan puisi tentang pengalaman dan penyembuhan dari trauma.", coverPath = "https://drive.google.com/uc?id=16emt1hzpJkwVHlwAPs24SsMUT0vrXH-h", queueCount = 12, availableCount = 3),
                Book(id = 14, title = "Hukum Pidana Adat", author = "Prof. Dr. Barda Nawawi Arief, S.H.", year = 2002, synopsis = "Kajian tentang hukum pidana yang bersumber dari adat.", coverPath = "https://drive.google.com/uc?id=1v9vR8M45zcjQkg_614wFiuPQ3tAKuGkx", queueCount = 2, availableCount = 7),
                Book(id = 15, title = "Sendiri", author = "Tere Liye", year = 2021, synopsis = "Novel tentang perjalanan seseorang dalam menemukan makna hidup.", coverPath = "https://drive.google.com/uc?id=1YRcmzO57Zgp2OZ8U97gxxZRQzBdBb-2y", queueCount = 14, availableCount = 0)
            )
            bookDao.insertBooks(books)
        }catch(e: Exception){
            Log.e("DatabaseSeeder", "error inserting data: ${e.message}", e )
        }
    }

    private suspend fun seedCollections(collectionDao: CollectionDao){
        try{
            val collections = listOf(
                Collection(
                    id = 1,
                    title = "Fiksi Klasik",
                    bookCount = 5,
                    coverPath = "https://drive.google.com/uc?id=1YRcmzO57Zgp2OZ8U97gxxZRQzBdBb-2y"
                ),
                Collection(
                    id = 2,
                    title = "Non-Fiksi Populer",
                    bookCount = 8,
                    coverPath = "https://drive.google.com/uc?id=1gj-JIO-u43oDV6AAPPqlC48NLVCbw1T3"
                ),
                Collection(
                    id = 3,
                    title = "Kumpulan Puisi",
                    bookCount = 3,
                    coverPath = "https://drive.google.com/uc?id=16emt1hzpJkwVHlwAPs24SsMUT0vrXH-h"
                )
            )
            collectionDao.insertCollections(collections)
        }catch (e:Exception){
            Log.e("DatabaseSeeder", "Error inserting collections: ${e.message}", e)
        }
    }

    private suspend fun seedSocialPosts(socialPostDao: SocialPostDao){
        try {
            val posts = listOf(
                SocialPost(
                    id = 1,
                    userName = "booklover123",
                    userProfileImage = "",
                    time = "2 jam lalu",
                    postText = "Sangat terinspirasi dengan buku ini! #HabisGelapTerbitlahTerang",
                    bookTitle = "Habis Gelap Terbitlah Terang",
                    bookCoverUrl = "https://drive.google.com/uc?id=1uYhAdLBR9RPN2_j-2GyQ3WH0bqkDOcGm",
                    likeCount = 25,
                    commentCount = 5
                ),
                SocialPost(
                    id = 2,
                    userName = "techGeek",
                    userProfileImage = "",
                    time = "5 jam lalu",
                    postText = "UI/UX sangat menarik, aku bisa belajar banyak dari buku ini! #UIUXDesign",
                    bookTitle = "Teori dan Praktik Desain UI/UX",
                    bookCoverUrl = "https://drive.google.com/uc?id=1X_obTam9MOKiiAMpNE6uB46WwUEQl5FG",
                    likeCount = 12,
                    commentCount = 2
                ),
                SocialPost(
                    id = 3,
                    userName = "financeGuru",
                    userProfileImage = "",
                    time = "1 hari lalu",
                    postText = "Panduan yang sangat berguna untuk mengelola keuangan. #MoneyGenius",
                    bookTitle = "Money Genius",
                    bookCoverUrl = "https://drive.google.com/uc?id=12OhzSoImQZrZdBE14R-motKmMMn56sAd",
                    likeCount = 30,
                    commentCount = 10
                )
            )
            socialPostDao.insertSocialPosts(posts)
        }catch (e: Exception){
            Log.e("DatabaseSeeder", "Error inserting posts: ${e.message}", e)
        }
    }

    private suspend fun seedUserProfile(userProfileDao: UserProfileDao){
        try{
            val userProfile = UserProfile(
                userName = "JohnDoe",
                userBio = "Seorang pecinta buku dan teknologi",
                userBacaCount = 120,
                userFollowingCount = 50,
                userFollowersCount = 200,
                profileImage = ""
            )
            userProfileDao.insertProfile(userProfile)
        }catch (e: Exception){
            Log.e("DatabaseSeeder", "Error inserting user profile: ${e.message}", e)
        }
    }

    private suspend fun seedAccountPost(accountPostDao: AccountPostDao){
        if (accountPostDao.getAllAccountPosts().isEmpty()) {
            val accountPosts = listOf(
                AccountPost(postText = "Lagi baca \"Detektif Conan 104\", baru sampai halaman 20."),
                AccountPost(postText = "Belajar bahasa baru nih, lewat buku \"Machine Learning for Beginners\"."),
                AccountPost(postText = "Ada yang udah baca buku ini belum ya?? Aku mau review nih!")
            )
            accountPostDao.insertAccountPosts(accountPosts)
        }

    }
}