package com.polman.oop.diagram2code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BookCreationTest {

    @Test
    fun `membuat Book valid - stok awal sama dengan totalCount dan info memuat stok`() {
        val b = Book(
            id = "B001",
            title = "Clean Code",
            author = "Robert C. Martin",
            year = 2008,
            totalCount = 2
        )
        // Uji inStock()
        assertTrue(b.inStock(), "Buku harus inStock saat totalCount > 0")
        // Uji available()
        assertEquals(2, b.available(), "available() harus sama dengan totalCount saat awal")

        val info = b.info()
        assertTrue(info.contains("Book"), "info harus menampilkan ringkasan")
        assertTrue(info.contains("2/2"), "info harus memuat stok") // Tersedia:2/2
    }

    @Test
    fun `fun gagal membuat Book - id blank atau year tidak wajar`() {
        // Uji ID blank
        val exId = assertThrows<IllegalArgumentException> {
            Book(id = " ", title = "X", author = "Y", year = 2000, totalCount = 1)
        }
        assertTrue(exId.message?.contains("ID") == true, "Exception harus memuat pesan tentang 'ID'")

        // Uji Tahun tidak wajar (< 1400)
        val exYear = assertThrows<IllegalArgumentException> {
            Book(id = "B002", title = "X", author = "Y", year = 1200, totalCount = 1)
        }
        assertTrue(exYear.message?.contains("Tahun") == true, "Exception harus memuat pesan tentang 'Tahun'")
    }

    @Test
    fun `totalCount nol - tidak inStock dan loan pertama harus false`() {
        val m = Member("M003", "Cici", MemberLevel.GOLD)
        val b = Book(
            id = "B005",
            title = "No Copies",
            author = "Anon",
            year = 2020,
            totalCount = 0
        )
        // Uji inStock()
        assertFalse(b.inStock(), "inStock() harus false jika totalCount=0")
        // Uji available()
        assertEquals(0, b.available(), "available() harus 0 jika totalCount=0")
        // Uji loan()
        assertFalse(b.loan(m), "loan() harus false jika totalCount=0")
        // Uji available() setelah loan
        assertEquals(0, b.available(), "available() harus tetap 0 setelah loan gagal")
    }
}