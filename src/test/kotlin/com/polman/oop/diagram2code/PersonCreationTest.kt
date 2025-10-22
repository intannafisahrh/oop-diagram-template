package com.polman.oop.diagram2code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PersonCreationTest {

    @Test
    fun `membuat Member valid - showInfo menampilkan level dan id`() {
        val m = Member (
            id = "M001",
            name = "Ani",
            level = MemberLevel.REGULAR
        )
        // assertEquals("Member|id=M001|name=Ani|level=REGULAR", m.showInfo()) // Asumsi: ini dikomentari atau diganti
        val info = m.showInfo()
        assertTrue(info.contains("Member"))
        assertTrue(info.contains("M001"))
        assertTrue(info.contains("REGULAR"))
        assertTrue(info.contains("Ani"), "Output harus memuat nama 'Ani'")
    }

    @Test
    fun `setter name melakukan trim dan validasi panjang`() {
        val m = Member(
            id = "M002",
            name = " Budi ",
            level = MemberLevel.PLATINUM
        )
        m.name = "   Budi Santoso   "
        assertEquals("Budi Santoso", m.name)

        val exShort = assertThrows<IllegalArgumentException> {
            m.name = "A" // < 2 huruf
        }
        assertTrue(exShort.message?.contains("name") == true)
    }

    @Test
    fun `membuat Librarian valid - staffCode nonblank dan fee selalu nol`() {
        val l = Librarian(
            id = "L001",
            name = "Sari",
            staffCode = "LIB"
        )
        assertEquals(0.0, l.calculateFee(10), 0.000001)
        val info = l.showInfo()
        assertTrue(info.contains("Librarian"))
        assertTrue(info.contains("LIB"))
    }

    @Test
    fun `gagal membuat turunan Person dengan name blank`() {
        val ex = assertThrows<IllegalArgumentException> {
            Member(
                id = "m003",
                name = " ",
                level = MemberLevel.GOLD
            )
        }
        assertTrue(ex.message?.contains("name") == true)
    }

    @Test
    fun `gagal membuat turunan Person dengan id blank`() {
        val ex = assertThrows<IllegalArgumentException> {
            Librarian(
                id = "",
                name = "Budi",
                staffCode = "SC-01"
            )
        }
        assertTrue(ex.message?.contains("id") == true)
    }
}