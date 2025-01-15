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
                Book(id = 1, title = "Laskar Pelangi", author = "Andrea Hirata", year = 2005, synopsis = "Menceritakan tentang kehidupan 10 anak dari keluarga miskin yang bersekolah di SD dan SMP Muhammadiyah di Desa Gantung, Belitung.", coverPath = "https://drive.google.com/uc?id=1MSbkJWG5lmySBI4xDONicQaPBXWTuw04", queueCount = 12, availableCount = 1,
                    pageContent = """
                        Bab 1: Awal yang Penuh Harapan===
                        Di sebuah desa kecil yang terpencil di Belitung Timur, berdirilah sebuah sekolah Muhammadiyah yang hampir roboh. Sekolah itu adalah satu-satunya harapan bagi anak-anak dari keluarga kurang mampu untuk mendapatkan pendidikan.===
                        Hari itu adalah hari pertama masuk sekolah. Murid-murid baru berkumpul di halaman sekolah dengan wajah penuhExcitement.===
                        Ibu Muslimah, sang kepala sekolah dan juga guru, menyambut mereka dengan senyum hangat.
                    """.trimIndent()
                ),
                Book(id = 2, title = "Membuat Game Augmented Reality (AR) dengan Unity 3D", author = "Ir. Ulfah Mediaty Arief, M.T. dkk.", year = 2019, synopsis = "Buku ini akan menjadi referensi yang baik untuk mahasiswa Jurusan Teknik Informatika, Ilmu Komputer, dan sejenisnya. Selain itu, buku ini juga dapat menjadi referensi bagi pecinta game yang ingin mencoba membuat game sendiri.", coverPath = "https://drive.google.com/uc?id=1f_79qZFyhGK9QynB5HNGUy70hCNU0OAk", queueCount = 0, availableCount = 19,
                    pageContent = """
                        Bab 1: Pengenalan Augmented Reality===
                        Augmented Reality (AR) adalah teknologi yang menggabungkan dunia virtual dengan dunia nyata secara real-time.===
                        Dengan AR, pengguna dapat melihat objek virtual yang ditempatkan di lingkungan dunia nyata melalui perangkat seperti smartphone atau tablet.===
                        Unity 3D adalah salah satu platform pengembangan game yang populer dan memiliki dukungan yang baik untuk pengembangan aplikasi AR.
                    """.trimIndent()
                ),
                Book(id = 3, title = "Bandit Bandit Berkelas", author = "Tere Liye", year = 2024, synopsis = "Mereka memang adalah bedebah.\nBandit-bandit.\nTapi mereka bukan pengkhianat, orang-orang bermuka dua, penjilat dan tabiat murahan lainnya.\nMereka adalah bandit-bandit dengan kehormatan. Setia kawan. Pemegang janji terbaik.\nMereka adalah bandit-bandit berkelas.", coverPath = "https://drive.google.com/uc?id=1HFs2o0nm4WsbNqUT7GG0KtmCoRXojdpy", queueCount = 12, availableCount = 11,
                    pageContent = """
                        Bagian 1: Pertemuan di Sudut Kota===
                        Malam itu sunyi, hanya suara deru kendaraan yang sesekali memecah keheningan. Di sebuah sudut kota yang remang-remang, tiga sosok pria bertemu.===
                        Mereka adalah para bandit, namun bukan bandit biasa. Mereka memiliki kode etik dan kehormatan.===
                        "Kita punya pekerjaan baru," kata seorang pria berbadan tegap dengan bekas luka di wajahnya.
                    """.trimIndent()
                ),
                Book(id = 4, title = "Sebuah Usaha Melupakan", author = "Boy Candra", year = 2016, synopsis = "Buku ini bercerita tentang “Aku” yang sangat mencintai pasangannya, dia awalnya memuji dan mengelukan pasangannya dengan kata-kata manis, pujian, dan cinta, meski mereka berhubungan jarak jauh. Hubungan mereka manis, penuh janji dan harapan. Namun, ketika si “aku” dihianati oleh pasangannya akibat kehadiran orang ketiga, dia beralih menjadi pembenci, dan berkata kasar. “aku” yang tadinya sangat romantis, manis, penuh kata-kata pujian tentang bagaimana bahagianya dia menemukan pasangannya, beralih mengeluarkan kata-kata yang kasar, seperti “Kau bukan orang yang layak diperjuangkan sepenuh hati” atau “Waktu akan mengutukmu, hingga tak ada satu hal pun yang menjadi bahagia yang bersedia mengetuk dadamu”.", coverPath = "https://drive.google.com/uc?id=1GHfw-A7LV1w8J3ywnrew1LBMw8YM_A5F", queueCount = 27, availableCount = 13,
                    pageContent = """
                        Bagian 1: Tentang Kehilangan===
                        Kehilangan adalah bagian dari kehidupan. Namun, melupakan seseorang yang pernah mengisi hati bukanlah perkara mudah.===
                        Ada banyak cara untuk melupakan, namun terkadang usaha itu terasa begitu berat.===
                        Mungkin dengan menulis, atau mungkin dengan mencari kesibukan baru.
                    """.trimIndent()
                ),
                Book(id = 5, title = "Sejarah Filsafat Barat", author = "Stephen Evans, C.", year=2024, synopsis = "Memuat tokoh-tokoh utama dan pandangan-pandangan dari filsafat-filsafat besar yang membentuk sejarah pemikiran manusia, yang ditulis dengan sangat jelas dan ringkas.", coverPath = "https://drive.google.com/uc?id=1nHJVxq_W2nltaq2_aCAdOuKqyRin6SG_", queueCount = 0, availableCount = 3,
                    pageContent = """
                        Bab 1: Filsafat Pra-Sokrates===
                        Filsafat Barat dimulai dengan para pemikir Yunani kuno yang dikenal sebagai filsuf Pra-Sokrates.===
                        Mereka berusaha untuk menjelaskan alam semesta dan segala isinya melalui akal budi, bukan mitos atau agama.===
                        Beberapa tokoh penting dalam periode ini adalah Thales, Anaximander, dan Anaximenes.
                    """.trimIndent()
                ),
                Book(id = 6, title = "B.J. Habibie", author = "R. William Liddle", year = 1998, synopsis = "Menganalisis peran B.J. Habibie dalam transisi politik Indonesia.", coverPath = "https://drive.google.com/uc?id=1gj-JIO-u43oDV6AAPPqlC48NLVCbw1T3", queueCount = 5, availableCount = 3,
                    pageContent = """
                        Bab 1: Awal Kehidupan dan Pendidikan===
                        Bacharuddin Jusuf Habibie lahir di Parepare, Sulawesi Selatan, pada tanggal 25 Juni 1936.===
                        Ia dikenal sebagai sosok yang cerdas dan memiliki minat yang besar terhadap ilmu pengetahuan dan teknologi.===
                        Habibie menempuh pendidikan tinggi di Technische Hochschule Aachen, Jerman.
                    """.trimIndent()
                ),
                Book(id = 7, title = "Buya Hamka", author = "EmhaF", year = 2021, synopsis = "Membahas pemikiran dan gagasan Buya Hamka.", coverPath = "https://drive.google.com/uc?id=1zjFzRypnbEkFmVVRsK_nWeIBUreL-vFa/view?usp=drive_link", queueCount = 0, availableCount = 2,
                    pageContent = """
                        Bab 1: Sosok Ulama dan Pemikir===
                        Buya Hamka adalah seorang ulama, sastrawan, dan pemikir Islam Indonesia yang sangat dihormati.===
                        Pemikiran-pemikirannya banyak memberikan pengaruh dalam perkembangan Islam di Indonesia.===
                        Karya-karyanya yang terkenal antara lain adalah Tafsir Al-Azhar dan Tenggelamnya Kapal Van der Wijck.
                    """.trimIndent()
                ),
                Book(id = 8, title = "Teori dan Praktik Desain UI/UX", author = "Rifda Faticha Alfa Aziza", year = 2023, synopsis = "Panduan lengkap tentang desain antarmuka dan pengalaman pengguna.", coverPath = "https://drive.google.com/uc?id=1X_obTam9MOKiiAMpNE6uB46WwUEQl5FG", queueCount = 8, availableCount = 1,
                    pageContent = """
                        Bab 1: Pengantar Desain UI/UX===
                        Desain Antarmuka Pengguna (UI) dan Pengalaman Pengguna (UX) adalah dua aspek penting dalam pengembangan produk digital.===
                        UI berfokus pada tampilan dan interaksi pengguna dengan produk, sedangkan UX berfokus pada keseluruhan pengalaman pengguna saat menggunakan produk.===
                        Buku ini akan membahas teori dan praktik desain UI/UX secara komprehensif.
                    """.trimIndent()
                ),
                Book(id = 9, title = "Dinamika Regulasi Ketenagakerjaan", author = "Dr. Haiyani Rumondang, M.A.", year = 2023, synopsis = "Analisis mendalam tentang perubahan regulasi ketenagakerjaan.", coverPath = "https://drive.google.com/uc?id=1X188pTgL7Mq59hWZ2oCJIR1w97wLOFxE", queueCount = 3, availableCount = 5,
                    pageContent = """
                        Bab 1: Pendahuluan Regulasi Ketenagakerjaan===
                        Regulasi ketenagakerjaan memiliki peran penting dalam mengatur hubungan antara pekerja dan pengusaha.===
                        Perubahan regulasi ketenagakerjaan dapat berdampak signifikan terhadap kondisi kerja dan ekonomi.===
                        Buku ini akan menganalisis dinamika regulasi ketenagakerjaan di Indonesia.
                    """.trimIndent()
                ),
                Book(id = 10, title = "Filsafat Berfikir", author = "EmhaF", year = 2022, synopsis = "Mengupas konsep dan pentingnya berfikir filosofis.", coverPath = "https://drive.google.com/uc?id=1Pc2FUWmwyvBqM1F5ZEBQv9_R-5Y6VFlG", queueCount = 10, availableCount = 4,
                    pageContent = """
                        Bab 1: Konsep Dasar Berfikir Filosofis===
                        Berfikir filosofis adalah proses berpikir yang mendalam dan kritis tentang pertanyaan-pertanyaan mendasar tentang eksistensi, pengetahuan, nilai, akal, pikiran, dan bahasa.===
                        Berfikir filosofis melibatkan analisis konsep, argumentasi logis, dan refleksi diri.===
                        Buku ini akan mengupas konsep dan pentingnya berfikir filosofis dalam kehidupan.
                    """.trimIndent()
                ),
                Book(id = 11, title = "Habis Gelap Terbitlah Terang", author = "R.A. Kartini", year = 1911, synopsis = "Kumpulan surat-surat Kartini tentang emansipasi wanita.", coverPath = "https://drive.google.com/uc?id=1uYhAdLBR9RPN2_j-2GyQ3WH0bqkDOcGm", queueCount = 20, availableCount = 1,
                    pageContent = """
                        Surat 1: Kepada Nyonya Abendanon===
                        Saudari yang terhormat, betapa bahagianya hati saya menerima surat dari Anda.===
                        Saya sangat terkesan dengan perhatian dan dukungan Anda terhadap perjuangan kami, kaum wanita.===
                        Kami bercita-cita untuk mendapatkan kesempatan yang sama dalam pendidikan dan kehidupan.
                    """.trimIndent()
                ),
                Book(id = 12, title = "Money Genius", author = "Rana Kinasih", year = 2023, synopsis = "Panduan praktis untuk mengelola keuangan dengan cerdas.", coverPath = "https://drive.google.com/uc?id=12OhzSoImQZrZdBE14R-motKmMMn56sAd", queueCount = 7, availableCount = 2,
                    pageContent = """
                        Bab 1: Mindset Seorang Money Genius===
                        Untuk menjadi seorang "Money Genius", Anda perlu memiliki mindset yang benar tentang uang.===
                        Uang bukanlah tujuan akhir, tetapi alat untuk mencapai tujuan Anda.===
                        Mulailah dengan mengubah cara pandang Anda tentang uang.
                    """.trimIndent()
                ),
                Book(id = 13, title = "Trauma", author = "Boy Candra", year = 2023, synopsis = "Kumpulan puisi tentang pengalaman dan penyembuhan dari trauma.", coverPath = "https://drive.google.com/uc?id=16emt1hzpJkwVHlwAPs24SsMUT0vrXH-h", queueCount = 12, availableCount = 3,
                    pageContent = """
                        Puisi 1: Luka yang Menganga===
                        Luka itu masih menganga, perihnya terasa hingga kini.===
                        Meskipun waktu telah berlalu, bayangan masa lalu terus menghantui.===
                        Namun, aku percaya, suatu saat nanti luka ini akan sembuh.
                    """.trimIndent()
                ),
                Book(id = 14, title = "Hujan", author = "Tere Liye", year = 2016, synopsis = "Tokoh utama dari buku Hujan adalah Lail. Lail adalah seorang wanita yang kehilangan orang tua karena bencana gempa bumi. Sejak saat itu ia hidup di Panti Sosial dengan ditemani Maryam yang juga memiliki karakter hampir sama seperti Lail.", coverPath = "https://drive.google.com/uc?id=1hhnSyEr3qW5hX5Am79Yu_ofoZFYxDOjq", queueCount = 0, availableCount = 7,
                    pageContent = """
                        RUANGAN 4 x 4 m2 itu selintas terlihat didesain terlalu sederhana untuk sebuah ruangan paling mutakhir di kota ini. Padahal ruangan itu berteknologi tinggi dan berperalatan medis paling maju. Teknologi terapinya tidak pernah dibayangkan manusia sebelumnya.
Dinding dan langit-langitnya berwarna putih. Tingginya sekitar empat meter. Hanya ada dua perabot di tengah ruangan. Satu kursi lipat diduduki seorang perempuan berusia lima puluh tahun. Dia mengenakan pakaian berwarna krem dan memegang tablet layar sentuh. Dia seorang paramedis senior. Satu lagi sofa pendek berwarna hijau. Seorang gadis muda dengan kemeja biru dan celana gelap duduk bersandar di sofa itu.
Sisanya hamparan lantai pualam tanpa cacat, seperti kubus kosong. Lampu yang ditanam di langit-langit mengeluarkan cahaya lembut. Waktu menunjukkan pukul delapan malam. Tidak ada jendela di ruangan itu.
”Namaku Elijah.” Paramedis senior itu tersenyum, memulai percakapan. ”Namamu Lail, bukan?”
Gadis di atas sofa hijau mengangguk perlahan.
”Kamu merayakan ulang tahun yang ke-21 minggu depan. Kamu yatim-piatu, tinggal di apartemen bersama seorang teman, dan menyelesaikan pendidikan level 4. Kamu juga memegang Lisensi Kelas A Sistem Kesehatan,” Elijah berkata, sambil jemari tangannya mengetuk lincah layar tablet di hadapannya. Tulisan-tulisan serta gambar di layar yang hanya setipis kertas HVS itu bergerak.
”Ah, kamu juga seorang perawat yang bertugas di rumah sakit kota.” Elijah diam sejenak, berhenti menggerakkan tulisan di layar, membaca lamat-lamat.
                    """.trimIndent()
                ),
                Book(id = 15, title = "Sendiri", author = "Tere Liye", year = 2021, synopsis = "Novel tentang perjalanan seseorang dalam menemukan makna hidup.", coverPath = "https://drive.google.com/uc?id=1YRcmzO57Zgp2OZ8U97gxxZRQzBdBb-2y", queueCount = 14, availableCount = 3,
                    pageContent = """
                        Bagian 1: Perjalanan Dimulai===
                        Perjalanan ini membawaku ke tempat-tempat yang belum pernah kubayangkan sebelumnya.===
                        Aku bertemu dengan orang-orang yang mengubah cara pandangku tentang hidup.===
                        Setiap langkah adalah pelajaran, setiap pertemuan adalah pengalaman.
                    """.trimIndent()
                )
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
}