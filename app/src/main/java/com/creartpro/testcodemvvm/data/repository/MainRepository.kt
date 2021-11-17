package com.creartpro.testcodemvvm.data.repository

import androidx.lifecycle.LiveData
import com.creartpro.testcodemvvm.data.entities.User
import com.creartpro.testcodemvvm.data.local.UserLocalDataSource
import com.creartpro.testcodemvvm.data.remote.MainRemoteDataSource

class MainRepository(private val remote: MainRemoteDataSource, private val local: UserLocalDataSource): IMainRepository {
    override fun getPlaceRepository() = remote.getPlace()
    override fun getGalleryRepository() = remote.getGallery()
    override fun getUserRepository() = remote.getUser()
    override fun getUserLocalRepository() = local.getUser()
    override fun setUserLocalRepository(user: User?) = local.setUser(user)
    override fun getLogoutLocal() = local.getLogout()
}