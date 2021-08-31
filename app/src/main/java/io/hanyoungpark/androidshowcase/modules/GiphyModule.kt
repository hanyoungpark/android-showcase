package io.hanyoungpark.androidshowcase.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.hanyoungpark.androidshowcase.repositories.GiphyRepository
import io.hanyoungpark.androidshowcase.repositories.GiphyRepositoryImpl
import io.hanyoungpark.androidshowcase.services.GiphyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
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

@Module
@InstallIn(ViewModelComponent::class)
abstract class GiphyModule2 {
    @Binds
    abstract fun bindGiphyRepository(giphyRepositoryImpl: GiphyRepositoryImpl): GiphyRepository
}
