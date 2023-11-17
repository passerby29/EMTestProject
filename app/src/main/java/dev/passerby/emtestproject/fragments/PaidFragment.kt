package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.databinding.FragmentPaidBinding

class PaidFragment : Fragment() {

    private var _binding: FragmentPaidBinding? = null
    private val binding: FragmentPaidBinding
        get() = _binding ?: throw RuntimeException("FragmentPaidBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paidToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.paidFinishButton.setOnClickListener {
            findNavController().navigate(
                PaidFragmentDirections.actionPaidFragmentToHotelFragment()
            )
        }
        binding.paidOrderTextView.text =
            String.format(getString(R.string.paid_order_placeholder), getRandomOrder())
    }

    private fun getRandomOrder(): Int {
        return (100000..999999).random()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}