package com.polman.oop.diagram2code

class Librarian(
    id: String,
    name: String,
    val staffCode: String,

    ):Person(id, name){

    init {
        // Validasi staffCode (Asumsi: 3 huruf kapital, non-blank)
        require(staffCode.length == 3 && staffCode.all { it.isUpperCase() }) {
            "StaffCode harus 3 huruf kapital."
        }
    }

    override fun showInfo(): String{
        // Studi Kasus 1
        return "Librarian|ID:$id|Nama:$name|Peran:Librarian|Kode Staf:$staffCode"
    }

    override fun calculateFee(daysLate: Int): Double{
        // Studi Kasus 2: Librarian selalu 0
        return 0.0
    }
}