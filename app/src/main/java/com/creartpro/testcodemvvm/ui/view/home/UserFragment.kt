package com.creartpro.testcodemvvm.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.creartpro.testcodemvvm.R
import com.creartpro.testcodemvvm.databinding.FragmentPlaceBinding
import com.creartpro.testcodemvvm.databinding.FragmentUserBinding
import com.creartpro.testcodemvvm.ui.adapter.PlaceAdapter
import com.creartpro.testcodemvvm.ui.view.login.LoginActivity
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel
import com.creartpro.testcodemvvm.utils.ViewModelFactory
import com.creartpro.testcodemvvm.vo.Status

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: MainViewModel
    private var adapterPlace = PlaceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
        }

        binding.btnLogout.setOnClickListener{
            logout()
        }
            getData()
    }

    fun getDataLocal(){
        val user = viewModel.getLocalUser()
        with(binding){
            Glide.with(requireContext()).load(user.avatar).into(imgProfile)
            tvUsername.text = user.username
            tvFullname.text = user.fullname
            tvEmail.text = user.email
            tvPhone.text = user.phone
        }
    }

    fun getData(){
        viewModel.getUserRemote().observe(viewLifecycleOwner, {
            it?.let{ res ->
                when(res.status){
                    Status.ERROR -> {
                        Log.d("Error", res.message.toString())
                    }
                    Status.LOADING -> {
                        binding.mainLayout.visibility = View.GONE
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        res.data?.data?.let{ user ->
                            with(binding){
                                Glide.with(requireContext()).load(user.avatar).into(imgProfile)
                                tvUsername.text = user.username
                                tvFullname.text = user.fullname
                                tvEmail.text = user.email
                                tvPhone.text = user.phone

                                progressbar.visibility = View.GONE
                                mainLayout.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }

        })
    }

    private fun logout(){
        viewModel.getLogout()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}