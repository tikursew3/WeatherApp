package com.example.weatherapp.forecast

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ForecastFragment: Fragment(R.layout.fragment_forecast) {
    private lateinit var fragmentBinding: FragmentForecastBinding

    @Inject lateinit var viewModel: ForecastViewModel
    private val args:ForecastFragmentArgs by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.forecast)

        fragmentBinding = FragmentForecastBinding.bind(view)
        fragmentBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.viewState.observe(viewLifecycleOwner) {
            bindData(it)
        }
        viewModel.onViewCreated(args.coordinates)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }




    }

    private fun bindData(state: ForecastViewModel.State) {
        fragmentBinding.recyclerView.adapter = state.forecast?.list?.let { ForecastAdapter(it) }

    }

}