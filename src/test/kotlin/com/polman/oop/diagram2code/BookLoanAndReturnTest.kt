package com.polman.oop.diagram2code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BookLoanAndReturnTest {

    @Test
    fun `peminjaman loan mengurangi stok dan gagal saat habis`() {
        val m = Member("M004", "Deni", MemberLevel.REGULAR)
        val b = Book("B003", "Test Loan", "Z", 2021, 2)

        // 1. Pinjam pertama (Stok: 2 -> 1)
        assertTrue(b.loan(m), "loan pertama harus true")
        assertEquals(1, b.available(), "Stok harus berkurang 1")

        // 2. Pinjam kedua (Stok: 1 -> 0)
        assertTrue(b.loan(m), "loan kedua harus true")
        assertEquals(0, b.available(), "Stok harus menjadi 0")
        assertFalse(b.inStock(), "inStock() harus false saat stok 0")

        // 3. Pinjam ketiga (Gagal)
        assertFalse(b.loan(m), "loan ketiga harus false (stok habis)")
        assertEquals(0, b.available(), "Stok harus tetap 0")
    }

    @Test
    fun `returnOne menambah stok dan memicu exception saat over-capacity`() {
        val b = Book("B004", "Test Return", "Y", 2022, 1)
        val m = Member("M005", "Fany", MemberLevel.GOLD)

        // Stok awal 1/1. Pinjam hingga habis (Stok: 1 -> 0)
        assertTrue(b.loan(m))
        assertEquals(0, b.available(), "Stok harus 0 setelah dipinjam")

        // 1. Pengembalian pertama (Stok: 0 -> 1)
        b.returnOne()
        assertEquals(1, b.available(), "Stok harus bertambah 1")
        assertTrue(b.inStock(), "inStock() harus true setelah dikembalikan")

        // 2. Pengembalian kedua (Stok: 1. Penuh. Gagal)
        val ex = assertThrows<IllegalStateException> {
            b.returnOne()
        }
        // Uji pesan exception
        assertTrue(ex.message?.contains("Over-capacity") == true, "Exception harus memuat pesan Over-capacity")
        assertEquals(1, b.available(), "Stok tidak boleh berubah setelah exception")
    }
}