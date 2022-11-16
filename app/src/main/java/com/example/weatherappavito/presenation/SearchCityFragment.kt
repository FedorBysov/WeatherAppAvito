package com.example.weatherappavito.presenation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentSearchCityBinding


class SearchCityFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
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
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]


//        binding.etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
//            }
//
//        })

//        viewModel.errorInputName.observe(viewLifecycleOwner){
//            TODO("Сделаь обработку ошибок!!!")
//        }

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