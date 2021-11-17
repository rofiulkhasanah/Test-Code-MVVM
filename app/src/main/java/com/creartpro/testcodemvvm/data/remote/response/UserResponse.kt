package com.creartpro.testcodemvvm.data.remote.response

import com.creartpro.testcodemvvm.data.entities.User
import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: User? = null,

	@field:SerializedName("message")
	val message: String? = null
)