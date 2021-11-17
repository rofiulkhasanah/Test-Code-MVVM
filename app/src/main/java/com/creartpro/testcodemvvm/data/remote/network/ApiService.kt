package com.creartpro.testcodemvvm.data.remote.network

import com.creartpro.testcodemvvm.data.remote.response.GalleryResponse
import com.creartpro.testcodemvvm.data.remote.response.PlaceResponse
import com.creartpro.testcodemvvm.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("place.json")
    suspend fun getPlace(): Response<PlaceResponse>

    @GET("gallery.json")
    suspend fun getGallery(): Response<GalleryResponse>

    @GET("user.json")
    suspend fun getUser(): Response<UserResponse>
}