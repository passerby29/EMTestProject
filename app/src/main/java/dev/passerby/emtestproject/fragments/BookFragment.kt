package dev.passerby.emtestproject.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.viewmodels.BookViewModel

class BookFragment : Fragment() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}