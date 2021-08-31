package io.hanyoungpark.androidshowcase.models

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @field:SerializedName("data") val data: List<DataModel>?,
    @field:SerializedName("pagination") val pagination: PaginationModel?
)
