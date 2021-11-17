package com.creartpro.testcodemvvm.vo

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

abstract class BaseDataSource {
    protected fun <T> getResult(call: suspend () -> Response<T>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data= null))
        try {
            val response = call()
            emit(Resource.success(data = response.body()))
        }catch (e: Exception){
            emit(Resource.error(msg= e.message ?: "Error", data= null))
        }
    }
}