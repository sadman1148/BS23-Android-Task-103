package com.bs23.androidtest103.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bs23.androidtest103.databinding.FragmentWebViewBinding
import com.bs23.androidtest103.utils.Constants

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Constants.KEY_BOOK_URL)?.let { binding.webView.loadUrl(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        return binding.root
    }

}