package com.creartpro.testcodemvvm.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.creartpro.testcodemvvm.data.entities.Gallery
import com.creartpro.testcodemvvm.data.entities.PlaceItem
import com.creartpro.testcodemvvm.data.entities.User
import com.creartpro.testcodemvvm.data.repository.MainRepository
import com.creartpro.testcodemvvm.vo.Status

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun setUser(user: User?) = mainRepository.setUserLocalRepository(user)
    fun getLocalUser() = mainRepository.getUserLocalRepository()
    fun getPlace() = mainRepository.getPlaceRepository()
    fun getGallery() = mainRepository.getGalleryRepository()
    fun getUserRemote() = mainRepository.getUserRepository()
    fun getLogout() = mainRepository.getLogoutLocal()


}