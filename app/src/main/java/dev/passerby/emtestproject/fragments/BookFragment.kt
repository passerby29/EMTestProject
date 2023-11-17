package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.customviews.TouristInfoItemView
import dev.passerby.emtestproject.databinding.FragmentBookBinding
import dev.passerby.emtestproject.hideErrorInTextField
import dev.passerby.emtestproject.showErrorInTextField
import dev.passerby.emtestproject.viewmodels.BookViewModel

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding: FragmentBookBinding
        get() = _binding ?: throw RuntimeException("FragmentBookBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[BookViewModel::class.java]
    }

    private var touristNumber = 1
    private var phoneNumber = ""
    private var email = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()

        binding.bookAddTouristContainer.setOnClickListener {
            val touristView = TouristInfoItemView(requireContext(), null)
            touristView.setTouristTitle(touristNumber++)
            binding.bookTouristsContainer.addView(touristView)
        }

        binding.bookToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        with(binding) {

            bookPhoneEditText.setOnFocusChangeListener { _, isFocused ->
                phoneNumber = bookPhoneEditText.unMaskedText?.trim().toString()

                if (isFocused) {
                    bookPhoneEditText.hint = "+7 (***) ***-**-**"
                } else {
                    bookPhoneEditText.hint = ""

                    if (phoneNumber.isEmpty()) {
                        hideErrorInTextField(bookPhoneEditText)
                    } else {
                        if (!checkPhoneNumberIsValid(phoneNumber)) {
                            showErrorInTextField(
                                bookPhoneEditText,
                                getString(R.string.not_valid_data_error)
                            )
                        } else {
                            hideErrorInTextField(bookPhoneEditText)
                        }
                    }
                }
            }

            bookEmailEditText.setOnFocusChangeListener { _, isFocused ->
                email = bookEmailEditText.text?.trim().toString()

                if (!isFocused) {
                    if (email.isEmpty()) {
                        hideErrorInTextField(bookEmailEditText)
                    } else {
                        if (!checkEmailIsValid(email)) {
                            showErrorInTextField(
                                binding.bookEmailEditText,
                                getString(R.string.not_valid_data_error)
                            )
                        } else {
                            hideErrorInTextField(bookEmailEditText)
                        }
                    }
                }
            }

            bookPayButton.setOnClickListener {
                if (!checkPhoneNumberIsValid(phoneNumber)) {
                    showErrorInTextField(
                        bookPhoneEditText,
                        getString(R.string.not_valid_data_error)
                    )
                    return@setOnClickListener
                }
                if (!checkEmailIsValid(email)) {
                    showErrorInTextField(
                        bookEmailEditText,
                        getString(R.string.not_valid_data_error)
                    )
                    return@setOnClickListener
                }
                bookTouristsContainer.forEach {
                    val touristItem = it as TouristInfoItemView
                    if (!touristItem.checkAllFieldsAreFilled()) {
                        return@setOnClickListener
                    }
                }

                findNavController().navigate(
                    BookFragmentDirections.actionBookFragmentToPaidFragment()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.bookInfo.observe(viewLifecycleOwner) { bookList ->
            if (bookList.isNotEmpty()) {
                val bookInfo = bookList[0]
                with(binding) {
                    val rank = "${bookInfo.hoRating} ${bookInfo.ratingName}"
                    val dates = "${bookInfo.tourDateStart} - ${bookInfo.tourDateStop}"
                    val priceSum = bookInfo.tourPrice + bookInfo.fuelCharge + bookInfo.serviceCharge

                    val nights =
                        String.format(getString(R.string.nights_placeholder), bookInfo.numberOfNights)
                    val tourPrice =
                        String.format(getString(R.string.price_placeholder), bookInfo.tourPrice)
                    val fuelCharge =
                        String.format(getString(R.string.price_placeholder), bookInfo.fuelCharge)
                    val serviceCharge =
                        String.format(getString(R.string.price_placeholder), bookInfo.serviceCharge)
                    val price = String.format(getString(R.string.price_placeholder), priceSum)
                    val buttonPrice =
                        String.format(getString(R.string.pay_text_placeholder), priceSum)

                    bookHotelRankTextView.text = rank
                    bookHotelNameTextView.text = bookInfo.hotelMame
                    bookHotelLocationTextView.text = bookInfo.hotelAddress
                    bookInfoDepartureTextView.text = bookInfo.departure
                    bookInfoArrivalTextView.text = bookInfo.arrivalCountry
                    bookInfoDatesTextView.text = dates
                    bookInfoNightNumberTextView.text = nights
                    bookInfoHotelNameTextView.text = bookInfo.hotelMame
                    bookInfoRoomTextView.text = bookInfo.room
                    bookInfoFoodTextView.text = bookInfo.nutrition
                    bookTourPriceTextView.text = tourPrice
                    bookFuelChargeTextView.text = fuelCharge
                    bookServiceChargeTextView.text = serviceCharge
                    bookFullPriceTextView.text = price
                    bookPayButton.text = buttonPrice
                }
            }
        }
    }

    private fun checkPhoneNumberIsValid(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }

    private fun checkEmailIsValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}