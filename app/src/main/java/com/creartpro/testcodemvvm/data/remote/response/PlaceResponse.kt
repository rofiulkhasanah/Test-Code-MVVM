package com.creartpro.testcodemvvm.data.remote.response

import com.creartpro.testcodemvvm.data.entities.Header
import com.creartpro.testcodemvvm.data.entities.PlaceItem
import com.google.gson.annotations.SerializedName

data class PlaceResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: DataPlace? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataPlace(

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("content")
	val content: List<PlaceItem>? = null
)