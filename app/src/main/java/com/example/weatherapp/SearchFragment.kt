package com.example.weatherapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.fragment_search) {

    private lateinit var fragmentBinding: FragmentSearchBinding
    @Inject lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding = FragmentSearchBinding.bind(view)

        viewModel.enableButton.observe(this) {
            enable -> fragmentBinding.button.isEnabled = enable
        }

        fragmentBinding.zipCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { viewModel.updateZipCode(it) }

            }

            override fun afterTextChanged(s: Editable?) {



            }

        })
        fragmentBinding.button.setOnClickListener {
            viewModel.submitButtonClicked()
        }

    }

}