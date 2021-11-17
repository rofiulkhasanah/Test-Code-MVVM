package com.creartpro.testcodemvvm.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.creartpro.testcodemvvm.databinding.FragmentPlaceBinding
import com.creartpro.testcodemvvm.ui.adapter.PlaceAdapter
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel
import com.creartpro.testcodemvvm.utils.ViewModelFactory
import com.creartpro.testcodemvvm.vo.Status

class PlaceFragment : Fragment() {
    private lateinit var binding: FragmentPlaceBinding
    private lateinit var viewModel: MainViewModel
    private var adapterPlace = PlaceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
        }

        binding.apply {
            rvPlace.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvPlace.adapter = adapterPlace
        }

        getData()
    }

    fun getData(){
        viewModel.getPlace().observe(viewLifecycleOwner, {
            it?.let { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        res.data?.data?.content?.let { place ->
                            adapterPlace.setData(place)
                        }
                        binding.mainLayout.visibility = View.VISIBLE
                        binding.progressbar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.mainLayout.visibility = View.GONE
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Log.d("Error", res.message.toString())
                    }
                }
            }
        })
    }
}