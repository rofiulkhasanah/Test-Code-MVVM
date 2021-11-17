package com.creartpro.testcodemvvm.data.repository

import androidx.lifecycle.LiveData
import com.creartpro.testcodemvvm.data.entities.User
import com.creartpro.testcodemvvm.data.remote.response.GalleryResponse
import com.creartpro.testcodemvvm.data.remote.response.PlaceResponse
import com.creartpro.testcodemvvm.data.remote.response.UserResponse
import com.creartpro.testcodemvvm.vo.Resource
import retrofit2.Response

interface IMainRepository {
    fun getPlaceRepository(): LiveData<Resource<out PlaceResponse>>
    fun getGalleryRepository(): LiveData<Resource<out GalleryResponse>>
    fun getUserRepository(): LiveData<Resource<out UserResponse>>
    fun getUserLocalRepository(): User
    fun setUserLocalRepository(user: User?)
    fun getLogoutLocal()
}