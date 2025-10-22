package com.polman.oop.diagram2code

class Member(
    id: String,
    name: String,
    val level: MemberLevel,

    ):Person(id, name){

    override fun showInfo(): String{
        // Studi Kasus 1: showInfo() informatif
        return "Member|ID:$id|Nama:$name|Peran:Member|Level:${level.name}"
    }

    override fun calculateFee(daysLate: Int): Double{
        // Studi Kasus 2: Fee sesuai tarif, non-negatif
        if (daysLate <= 0) return 0.0
        return level.feePerDay * daysLate
    }
}