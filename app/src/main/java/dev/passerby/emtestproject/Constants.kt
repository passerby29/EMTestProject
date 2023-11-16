package dev.passerby.emtestproject

@Suppress("NonAsciiCharacters")
enum class TouristNumber {
    Первый,
    Второй,
    Третий,
    Четвёртый,
    Пятый,
    Следующий
}

fun getTouristNumber(index: Int): TouristNumber {
    return if (index in 0..4) TouristNumber.values()[index]
    else TouristNumber.Следующий
}