package com.creartpro.testcodemvvm.data.remote.response

import com.creartpro.testcodemvvm.data.entities.Gallery
import com.google.gson.annotations.SerializedName

data class GalleryResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: List<Gallery?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)