package com.creartpro.testcodemvvm.utils

import android.content.Context
import com.creartpro.testcodemvvm.data.local.UserLocalDataSource
import com.creartpro.testcodemvvm.data.local.preference.UserPreferences
import com.creartpro.testcodemvvm.data.remote.MainRemoteDataSource
import com.creartpro.testcodemvvm.data.remote.network.ApiConfig
import com.creartpro.testcodemvvm.data.repository.MainRepository

object Injection {

    fun provideMainRepository(context: Context): MainRepository {
        val preferences = UserPreferences(context)

        val apiService = ApiConfig.getApi()
        val remote = MainRemoteDataSource(apiService)
        val local = UserLocalDataSource(preferences)

        return MainRepository(remote, local)
    }
}