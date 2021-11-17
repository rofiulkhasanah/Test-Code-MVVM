package com.creartpro.testcodemvvm.data.entities

data class Place(
	val header: Header? = null,
	val content: List<PlaceItem?>? = null
)

data class PlaceItem(
	val image: String? = null,
	val id: Int? = null,
	val media: List<String?>? = null,
	val title: String? = null,
	val type: String? = null,
	val content: String? = null
)

data class Header(
	val subtitle: String? = null,
	val title: String? = null
)
