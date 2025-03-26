# Aplikasi Perpustakaan Digital

## Deskripsi
Aplikasi Perpustakaan Digital ini adalah aplikasi mobile yang dibuat sebagai tugas akhir mata kuliah Pemrograman Mobile. Aplikasi ini memungkinkan pengguna untuk melihat daftar dan detail buku, meminjam buku, serta membaca buku. Aplikasi ini dikembangkan menggunakan bahasa Kotlin di Android Studio dan terhubung ke Firebase untuk autentikasi pengguna.

## Anggota Kelompok
*   Rendie Abdi Saputra - 2200018094 (Developer)
*   Muhammad Farras Aji Rukmana - 2200018262 (Project Leader)
*   Evinda Apriliani - 2200018387 (Developer)
*   Reyhanssan Islamey - 22000018411 (Developer)
*   Zainnaya Putri Diyanti - 2200018426 (UI/UX Designer)

## Fitur Utama
*   **Autentikasi Pengguna:**
    *   Pendaftaran akun baru melalui email.
    *   Login menggunakan email dan password.
*   **Katalog Buku (Database lokal, load data agak lambat):**
    *   Menampilkan daftar buku yang tersedia.
    *   Menampilkan detail buku (judul, penulis, sinopsis, sampul, dan lain-lain).
*   **Peminjaman dan Antri Buku:**
    *   Fitur untuk "meminjam" buku (simulasi, belum terhubung ke sistem).
    *   Fitur untuk "antri pinjam" buku (simulasi, data antrian belum tercatat).
*   **Membaca Buku:**
    *   Fitur untuk "membaca" buku (belum semua buku ada isi bacaan yang sesuai, karena masih database lokal)

## Cara Menjalankan Aplikasi
1.  **Clone Repositori:**
    ```bash
    git clone [[URL repositori GitHub kamu]](https://github.com/EvindaAprl/library-app.git)
    ```
2.  **Buka Proyek di Android Studio:**
    Buka Android Studio dan pilih "Open an Existing Project". Navigasi ke direktori tempat kamu meng-clone repositori dan pilih file `build.gradle`.
3.  **Build dan Jalankan Aplikasi:**
    Klik tombol "Sync Project with Gradle Files" dan kemudian "Run" untuk menjalankan aplikasi.
