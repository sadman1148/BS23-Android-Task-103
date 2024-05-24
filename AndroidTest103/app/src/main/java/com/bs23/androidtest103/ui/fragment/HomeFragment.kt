package com.bs23.androidtest103.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bs23.androidtest103.R
import com.bs23.androidtest103.databinding.FragmentHomeBinding
import com.bs23.androidtest103.ui.adapter.BookAdapter
import com.bs23.androidtest103.utils.State
import com.bs23.androidtest103.utils.Utility
import com.bs23.androidtest103.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val bookAdapter: BookAdapter by lazy {
        BookAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getBookList()
        homeViewModel.bookListLiveData.observe(this) {
            when (it) {
                is State.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }

                is State.Success -> {
                    binding.loader.visibility = View.GONE
                    bookAdapter.addItems(it.data.data)
                }

                is State.Error -> {
                    binding.loader.visibility = View.GONE
                    if (!Utility.hasInternet(requireContext())) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.no_internet_connection),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = bookAdapter
        return binding.root
    }

}