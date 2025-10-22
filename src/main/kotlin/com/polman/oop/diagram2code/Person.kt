package com.polman.oop.diagram2code

abstract class Person(
    val id: String,
    name: String
){
    var name: String = name
        set(value) {
            // Panggil validasi, lalu lakukan trim dan set field
            validateName(value)
            field = value.trim()
        }

    init {
        // Validasi ID non-blank
        require(id.isNotBlank()) { "ID tidak boleh kosong atau blank." }

        // Panggil setter 'name' untuk memicu validasi saat konstruksi
        this.name = name
    }

    // Di file Person.kt, dalam fungsi validateName
    protected fun validateName(n: String){
        val trimmedName = n.trim()
        require(trimmedName.length in 2..100) {
            // UBAH: Gunakan kata 'name' (huruf kecil) agar sesuai dengan test
            "The name must be between 2 and 100 characters after trim. Found: ${trimmedName.length}"
        }
    }

    abstract fun showInfo (): String

    abstract fun calculateFee (daysLate: Int): Double
}