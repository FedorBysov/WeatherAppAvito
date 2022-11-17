package com.example.weatherappavito.pages.search.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappavito.databinding.FragmentSearchCityBinding
import com.example.weatherappavito.pages.weather.presentation.WeatherDetailedViewModel


class SearchCityFragment : Fragment() {

    private lateinit var viewModel: SearchCityViewModel
    private var _binding: FragmentSearchCityBinding? = null
    private val binding
        get() = _binding!!

    //location

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchCityViewModel::class.java]


        binding.clBackground.setOnClickListener {
            binding.clBackground.hideKeyboard()
        }

        binding.btnSearch.setOnClickListener {

            viewModel.loadData(binding.etSearch.text.toString())
            binding.btnSearch.hideKeyboard()
            Thread.sleep(200)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }


    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}