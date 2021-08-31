package io.hanyoungpark.androidshowcase.services

import io.hanyoungpark.androidshowcase.models.SearchModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("/v1/gifs/search")
    suspend fun search(@Query("api_key") apiKey: String,
                       @Query("q") query: String,
                       @Query("limit") limit: Int,
                       @Query("offset") offset: Int): SearchModel?
}
