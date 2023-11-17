package dev.passerby.emtestproject

import android.widget.EditText

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

fun showErrorInTextField(editText: EditText, errorMessage: String) {
    editText.apply {
        error = errorMessage
        backgroundTintList =
            resources.getColorStateList(R.color.error_red, null)
    }
}

fun hideErrorInTextField(editText: EditText) {
    editText.apply {
        error = null
        backgroundTintList =
            resources.getColorStateList(R.color.text_field_background, null)
    }
}