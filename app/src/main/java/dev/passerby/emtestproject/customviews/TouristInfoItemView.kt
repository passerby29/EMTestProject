package dev.passerby.emtestproject.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.textfield.TextInputLayout
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.databinding.ItemBookBinding
import dev.passerby.emtestproject.getTouristNumber
import dev.passerby.emtestproject.hideErrorInTextField
import dev.passerby.emtestproject.showErrorInTextField

class TouristInfoItemView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var binding: ItemBookBinding
    private var containerVisibility = true

    init {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemBookBinding.inflate(layoutInflater, this, true)
        binding.bookItemShowHideButton.setOnClickListener {
            showHideEditTexts()
        }
        setTouristTitle(0)
    }

    fun setTouristTitle(touristNumber: Int) {
        binding.bookItemTouristTitleTextView.text =
            String.format(
                context.getString(R.string.tourist_info_title_placeholder),
                getTouristNumber(touristNumber)
            )
    }

    fun checkAllFieldsAreFilled(): Boolean{
        var allFieldsAreFilled = true

        for (i in 0 until binding.bookItemEditTextContainer.childCount){
            val textInputLayout = binding.bookItemEditTextContainer.getChildAt(i) as TextInputLayout
            val textInputEditText = textInputLayout.editText
            if (textInputEditText?.text?.isBlank() == true){
                showErrorInTextField(textInputEditText, context.getString(R.string.et_error_message))
                allFieldsAreFilled = false
            } else {
                if (textInputEditText != null) {
                    hideErrorInTextField(textInputEditText)
                }
            }
        }

        return allFieldsAreFilled
    }

    private fun showHideEditTexts() {
        if (containerVisibility) {
            binding.bookItemShowHideButton.setImageResource(R.drawable.ic_arrow_down)
            binding.bookItemEditTextContainer.visibility = View.GONE
            containerVisibility = !containerVisibility
        } else {
            binding.bookItemShowHideButton.setImageResource(R.drawable.ic_arrow_up)
            binding.bookItemEditTextContainer.visibility = View.VISIBLE
            containerVisibility = !containerVisibility
        }
    }
}