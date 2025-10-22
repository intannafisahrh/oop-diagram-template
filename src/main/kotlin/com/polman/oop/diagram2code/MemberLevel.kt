package com.polman.oop.diagram2code

enum class MemberLevel(val feePerDay: Double) {
    // Tarif diasumsikan dari Oracle Output M11 | REGULAR | 3 | 6000.0 (2000/hari)
    REGULAR(2000.0),
    GOLD(1500.0),    // Contoh asumsi tarif GOLD
    PLATINUM(1000.0) // Contoh asumsi tarif PLATINUM
}