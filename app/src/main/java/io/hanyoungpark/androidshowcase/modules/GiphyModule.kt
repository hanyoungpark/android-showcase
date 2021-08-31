package io.hanyoungpark.androidshowcase.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.hanyoungpark.androidshowcase.services.GiphyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object GiphyModule {
    @Provides
    fun provideGiphyService(): GiphyService {
        return Retrofit.Builder()
                .baseUrl("https://api.giphy.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GiphyService::class.java)
    }


}