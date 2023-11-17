package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexboxLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.databinding.FragmentHotelBinding
import dev.passerby.emtestproject.viewmodels.HotelViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding: FragmentHotelBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding is null")

    private val viewModel: HotelViewModel by viewModels()

    private var hotelName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        binding.hotelRoomsButton.setOnClickListener {
            findNavController().navigate(
                HotelFragmentDirections.actionHotelFragmentToRoomsFragment(hotelName)
            )
        }
    }

    private fun observeViewModel() {
        viewModel.hotelInfo.observe(viewLifecycleOwner) { hotelList ->
            if (hotelList.isNotEmpty()) {
                val hotel = hotelList.first()
                with(binding) {
                    val carouselList = hotel.imageUrls.map { CarouselItem(it) }
                    val rank = "${hotel.rating} ${hotel.ratingName}"
                    val price =
                        String.format(getString(R.string.price_placeholder), hotel.minimalPrice)
                    hotelName = hotel.name
                    hotelImageCarousel.setData(carouselList)
                    hotelRankTextView.text = rank
                    hotelNameTextView.text = hotelName
                    hotelLocationTextView.text = hotel.address
                    hotelPriceTextView.text = price
                    hotelPriceAddTextView.text = hotel.priceForIt
                    hotelPeculiaritiesContainer.removeAllViews()
                    hotel.aboutTheHotel.peculiarities.forEach { peculiarity ->
                        val textView = TextView(context)
                        val params = FlexboxLayout.LayoutParams(
                            FlexboxLayout.LayoutParams.WRAP_CONTENT,
                            FlexboxLayout.LayoutParams.WRAP_CONTENT
                        )
                        val typeface = ResourcesCompat.getFont(requireContext(), R.font.sf_medium)
                        textView.apply {
                            text = peculiarity
                            textSize = 16f
                            setTypeface(typeface)
                            setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.additional_text_color
                                )
                            )
                            setBackgroundResource(R.drawable.item_peculiarity_background)
                            setPadding(10, 5, 10, 5)
                            params.setMargins(8, 4, 0, 4)
                            layoutParams = params
                        }
                        hotelPeculiaritiesContainer.addView(textView)
                    }
                    hotelDescTextView.text = hotel.aboutTheHotel.description
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}