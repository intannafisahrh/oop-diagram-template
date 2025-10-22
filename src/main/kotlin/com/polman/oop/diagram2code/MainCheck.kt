package com.polman.oop.diagram2code

fun main() {
    println("=== Implementasi Studi Kasus OOP Perpustakaan ===")

    // ==========================================================
    // STUDI KASUS 1: Registrasi Peran & Informasi Dasar
    // ==========================================================
    println("\n--- 1. Registrasi & Info ---")

    // 1.1 Buat 2 Member, 1 Librarian
    val m1 = Member("M11", "Ani", MemberLevel.REGULAR)
    val m2 = Member("M22", "Budi", MemberLevel.GOLD)
    val l1 = Librarian("L11", "Sari", "LIB")

    // 1.2 Panggil showInfo()
    println(m1.showInfo())
    println(m2.showInfo())
    println(l1.showInfo())

    // 1.3 Uji setter name (trim & validasi)
    println("\n--- Uji Setter Name ---")
    m1.name = "   Ani Putri   "
    println("Nama m1 baru: ${m1.name}")

    // 1.4 Uji gagal name="" (harus melempar exception)
    try {
        m1.name = " "
    } catch (e: IllegalArgumentException) {
        println("Uji Gagal Name (Sukses): ${e.message}")
    }

    // ==========================================================
    // STUDI KASUS 2: Denda Polimorfik
    // ==========================================================
    println("\n--- 2. Denda Polimorfik ---")
    val daysLateSet = listOf(0, 1, 3, 10)

    // Fee sesuai tarif level, cek untuk hari {0, 1, 3, 10}
    daysLateSet.forEach { days ->
        println("Denda M11 REGULAR ($days hari): Rp ${m1.calculateFee(days)}")
        println("Denda M22 GOLD ($days hari): Rp ${m2.calculateFee(days)}")
        println("Denda L11 Librarian ($days hari): Rp ${l1.calculateFee(days)}")
    }

    // ==========================================================
    // STUDI KASUS 3 & 4: Peminjaman & Pengembalian
    // ==========================================================
    println("\n--- 3 & 4. Peminjaman & Pengembalian ---")
    val b1 = Book("B001", "Kotlin", "A", 2024, 2) // total=2
    val b2 = Book("B002", "OOP", "B", 2023, 1)    // total=1

    println(b1.info()) // Stok awal b1: 2/2

    // Peminjaman b1 hingga habis
    println("Pinjam b1 ke m1 (Stok ${b1.available()}): ${b1.loan(m1)}")
    println("Pinjam b1 ke m2 (Stok ${b1.available()}): ${b1.loan(m2)}")
    println("Pinjam b1 lagi (Stok ${b1.available()}): ${b1.loan(m1)}") // False (Stok habis)

    // Verifikasi inStock() & available()
    println("b1 inStock: ${b1.inStock()}")
    println("b1 available: ${b1.available()}")

    // Peminjaman b2
    println(b2.info())
    println("Pinjam b2 ke m1 (Stok ${b2.available()}): ${b2.loan(m1)}")

    // Uji Pengembalian (returnOne)
    b1.returnOne()
    println("b1 setelah dikembalikan 1: ${b1.info()}") // Stok 1/2

    // Uji Over-Capacity pada b1
    b1.returnOne()
    println("b1 setelah dikembalikan 2: ${b1.info()}") // Stok 2/2 (Penuh)

    // Panggilan returnOne berikutnya harus memicu Exception (Kriteria 4)
    try {
        b1.returnOne()
    } catch (e: IllegalStateException) {
        println("Uji Gagal Over-Capacity (Sukses): ${e.message}")
    }

    // ==========================================================
    // STUDI KASUS 5: Laporan Ringkas & Konsistensi
    // ==========================================================
    println("\n=== 5. LAPORAN RINGKAS ===")
    val people = listOf(m1, m2, l1)
    val books = listOf(b1, b2)

    println("\n=== PERSONS ===")
    people.forEach { println(it.showInfo()) } // Cek konsistensi nama m1

    println("\n=== BOOKS ===")
    books.forEach { println(it.info()) } // Cek konsistensi stok terakhir b1 & b2
}