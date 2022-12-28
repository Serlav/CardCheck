package ru.serlav.cardcheck

data class CardInfo(
    var length: Int = 0,
    val luhn: Boolean = true,
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val prepaid: Boolean = false,
    val numeric: Int = 0,
    val alpha2: String = "",
    val name: String = "",
    val emoji: String = "",
    val currency: String = "",
    val latitude: Int = 0,
    val longitude: Int = 0,
    val bankName: String = "",
    val url: String = "",
    val phone: String = "",
    )