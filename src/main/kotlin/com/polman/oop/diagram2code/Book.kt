package com.polman.oop.diagram2code

class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val totalCount: Int
) : Loanable {

    // availableCount harus 'var' dan private set
    var availableCount: Int = totalCount
        private set

    init {
        // Validasi konstruktor: non-blank dan tahun [1400, 2100]
        require(id.isNotBlank() && title.isNotBlank() && author.isNotBlank()) {
            "ID, Judul, dan Penulis tidak boleh kosong."
        }
        require(year in 1400..2100) { "Tahun terbit harus antara 1400 hingga 2100." }
        require(totalCount >= 0) { "Jumlah total buku tidak boleh negatif." }
    }

    fun inStock(): Boolean {
        // Kriteria 3
        return availableCount > 0
    }

    override fun loan(to: Member): Boolean {
        // Kriteria 3: loan() gagal (false) bila stok habis.
        if (inStock()) {
            availableCount--
            return true
        }
        return false // Stok habis
    }

    fun returnOne() {
        // Kriteria 4: proteksi over-capacity
        if (availableCount < totalCount) {
            availableCount++
        } else {
            // Over-capacity terdeteksi, melempar exception
            throw IllegalStateException("ReturnOne gagal: Stok sudah penuh (Over-capacity).")
        }
    }

    fun available(): Int {
        // Kriteria 3 & 5
        return availableCount
    }

    fun info(): String {
        // Kriteria 5: Laporan ringkas
        return "Book|ID:$id|Judul:$title|Pengarang:$author|Tahun:$year|Tersedia:$availableCount/$totalCount"
    }
}