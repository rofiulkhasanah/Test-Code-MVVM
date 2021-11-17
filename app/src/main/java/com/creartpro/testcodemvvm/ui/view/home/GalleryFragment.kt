package com.creartpro.testcodemvvm.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.creartpro.testcodemvvm.R
import com.creartpro.testcodemvvm.data.entities.Gallery
import com.creartpro.testcodemvvm.databinding.FragmentGalleryBinding
import com.creartpro.testcodemvvm.ui.adapter.GalleryAdapter
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel
import com.creartpro.testcodemvvm.utils.ViewModelFactory
import com.creartpro.testcodemvvm.vo.Status

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: MainViewModel
    private var galleryAdapter = GalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
        }
        binding.apply {
            rvGallery.layoutManager =
                GridLayoutManager(requireContext(), 3)
            rvGallery.adapter = galleryAdapter
        }

        getData()
    }

    fun getData(){
        viewModel.getGallery().observe(viewLifecycleOwner, {
            it?.let { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        res.data?.data?.let { gallery ->
                            galleryAdapter.setData(gallery as List<Gallery>)
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