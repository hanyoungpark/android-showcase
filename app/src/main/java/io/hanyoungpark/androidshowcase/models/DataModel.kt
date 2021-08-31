package io.hanyoungpark.androidshowcase.models

import com.google.gson.annotations.SerializedName

data class DataModel (
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("images") val images: ImagesModel?,
    @field:SerializedName("title") val title: String?
)