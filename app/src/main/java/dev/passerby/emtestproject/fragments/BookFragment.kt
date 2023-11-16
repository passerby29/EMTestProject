package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.customviews.TouristInfoItemView
import dev.passerby.emtestproject.databinding.FragmentBookBinding
import dev.passerby.emtestproject.viewmodels.BookViewModel

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding: FragmentBookBinding
        get() = _binding ?: throw RuntimeException("FragmentBookBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[BookViewModel::class.java]
    }

    private var touristNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookPhoneEditText.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                binding.bookPhoneEditText.hint = "+7 (***) ***-**-**"
            } else {
                binding.bookPhoneEditText.hint = ""
            }
        }
        observeViewModel()
        binding.bookAddTouristContainer.setOnClickListener {
            val touristView = TouristInfoItemView(requireContext(), null)
            touristView.setTouristTitle(touristNumber++)
            binding.bookTouristsContainer.addView(touristView)
        }
    }

    private fun observeViewModel() {
        viewModel.bookInfo.observe(viewLifecycleOwner) {
            with(binding) {
                val rank = "${it.hoRating} ${it.ratingName}"
                val dates = "${it.tourDateStart} - ${it.tourDateStop}"
                val priceSum = it.tourPrice + it.fuelCharge + it.serviceCharge

                val nights =
                    String.format(getString(R.string.nights_placeholder), it.numberOfNights)
                val tourPrice = String.format(getString(R.string.price_placeholder), it.tourPrice)
                val fuelCharge = String.format(getString(R.string.price_placeholder), it.fuelCharge)
                val serviceCharge =
                    String.format(getString(R.string.price_placeholder), it.serviceCharge)
                val price = String.format(getString(R.string.price_placeholder), priceSum)
                val buttonPrice = String.format(getString(R.string.pay_text_placeholder), priceSum)

                bookHotelRankTextView.text = rank
                bookHotelNameTextView.text = it.hotelMame
                bookHotelLocationTextView.text = it.hotelAddress
                bookInfoDepartureTextView.text = it.departure
                bookInfoArrivalTextView.text = it.arrivalCountry
                bookInfoDatesTextView.text = dates
                bookInfoNightNumberTextView.text = nights
                bookInfoHotelNameTextView.text = it.hotelMame
                bookInfoRoomTextView.text = it.room
                bookInfoFoodTextView.text = it.nutrition
                bookTourPriceTextView.text = tourPrice
                bookFuelChargeTextView.text = fuelCharge
                bookServiceChargeTextView.text = serviceCharge
                bookFullPriceTextView.text = price
                bookPayButton.text = buttonPrice
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}