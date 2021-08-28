package io.hanyoungpark.androidshowcase.models

import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @field:SerializedName("original") val original: ImageModel?
)
