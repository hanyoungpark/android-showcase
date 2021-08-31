package io.hanyoungpark.androidshowcase.repositories

import dagger.Component
import io.hanyoungpark.androidshowcase.BuildConfig
import io.hanyoungpark.androidshowcase.models.SearchModel
import io.hanyoungpark.androidshowcase.services.GiphyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GiphyRepository {
    fun search(query: String, limit: Int, offset: Int): Flow<SearchModel>
}

class GiphyRepositoryImpl @Inject constructor (
        private val giphyService: GiphyService
) : GiphyRepository {
    override fun search(query: String,
                        limit: Int,
                        offset: Int): Flow<SearchModel> =
        flow {
            val result
                = giphyService.search(BuildConfig.API_KEY, query, limit, offset)
                ?: return@flow
            emit(result)
        }
}
