package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.databinding.FragmentHotelBinding
import dev.passerby.emtestproject.viewmodels.HotelViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding: FragmentHotelBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[HotelViewModel::class.java]
    }

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
    }

    private fun observeViewModel() {
        viewModel.hotelInfo.observe(viewLifecycleOwner) {
            with(binding) {
                val carouselList = it.imageUrls.map { CarouselItem(it) }
                val rank = "${it.rating} ${it.ratingName}"
                val price =
                    String.format(getString(R.string.hotel_price_placeholder), it.minimalPrice)
                hotelImageCarousel.setData(carouselList)
                hotelRankTextView.text = rank
                hotelNameTextView.text = it.name
                hotelLocationTextView.text = it.address
                hotelPriceTextView.text = price
                hotelPriceAddTextView.text = it.priceForIt
                for (i in 0 until hotelPeculiaritiesContainer.childCount){
                    val textView = hotelPeculiaritiesContainer.getChildAt(i) as TextView
                    textView.text = it.aboutTheHotel.peculiarities[i]
                }
                hotelDescTextView.text = it.aboutTheHotel.description
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}