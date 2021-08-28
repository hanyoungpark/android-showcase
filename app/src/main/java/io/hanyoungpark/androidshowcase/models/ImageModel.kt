package io.hanyoungpark.androidshowcase.models

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @field:SerializedName("height") val height: String?,
    @field:SerializedName("width") val width: String?,
    @field:SerializedName("size") val size: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("mp4_size") val mp4_size: String?,
    @field:SerializedName("mp4") val mp4: String?,
    @field:SerializedName("webp_size") val webp_size: String?,
    @field:SerializedName("webp") val webp: String?,
    @field:SerializedName("frame") val frame: String?,
    @field:SerializedName("hash") val hash: String?,

)
