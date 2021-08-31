package io.hanyoungpark.androidshowcase.models

import com.google.gson.annotations.SerializedName

data class PaginationModel (
    @field:SerializedName("offset") val offset: Int,
    @field:SerializedName("total_count") val total_count: Int,
    @field:SerializedName("count") val count: Int
)