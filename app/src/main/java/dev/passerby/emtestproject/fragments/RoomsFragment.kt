package dev.passerby.emtestproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.passerby.emtestproject.adapter.RoomsAdapter
import dev.passerby.emtestproject.databinding.FragmentRoomsBinding
import dev.passerby.emtestproject.viewmodels.RoomsViewModel

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentRoomsBinding is null")

    private val viewModel: RoomsViewModel by viewModels()

    private val args by navArgs<RoomsFragmentArgs>()
    private var roomsAdapter: RoomsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomsAdapter = RoomsAdapter(requireContext())
        roomsAdapter?.onRoomItemCLickListener = {
            findNavController().navigate(RoomsFragmentDirections.actionRoomsFragmentToBookFragment())
        }

        binding.roomsHotelNameTextView.text = args.hotelName
        binding.roomsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.roomsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = roomsAdapter
        }
        viewModel.roomsInfo.observe(viewLifecycleOwner) {
            roomsAdapter?.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}