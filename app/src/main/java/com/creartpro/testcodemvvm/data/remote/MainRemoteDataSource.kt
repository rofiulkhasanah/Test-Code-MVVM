package com.creartpro.testcodemvvm.data.remote

import androidx.lifecycle.liveData
import com.creartpro.testcodemvvm.data.remote.network.ApiService
import com.creartpro.testcodemvvm.vo.BaseDataSource
import com.creartpro.testcodemvvm.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainRemoteDataSource(private val service: ApiService): BaseDataSource() {
    fun getPlace() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = service.getPlace()
            emit(Resource.success(data = response.body()))
        } catch (e: Exception) {
            emit(Resource.error(msg = e.message ?: "Error", data = null))
        }
    }
    fun getGallery() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = service.getGallery()
            emit(Resource.success(data = response.body()))
        } catch (e: Exception) {
            emit(Resource.error(msg = e.message ?: "Error", data = null))
        }
    }
    fun getUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = service.getUser()
            emit(Resource.success(data = response.body()))
        } catch (e: Exception) {
            emit(Resource.error(msg = e.message ?: "Error", data = null))
        }
    }
}