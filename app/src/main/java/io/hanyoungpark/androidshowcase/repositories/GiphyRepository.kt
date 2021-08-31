package io.hanyoungpark.androidshowcase.repositories

import dagger.Component
import io.hanyoungpark.androidshowcase.BuildConfig
import io.hanyoungpark.androidshowcase.models.ImagesModel
import io.hanyoungpark.androidshowcase.models.SearchModel
import io.hanyoungpark.androidshowcase.services.GiphyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GiphyRepository {
    fun search(query: String, limit: Int, offset: Int): Flow<SearchModel>
    fun getImages(id: String): ImagesModel?
}

class GiphyRepositoryImpl @Inject constructor (
        private val giphyService: GiphyService
) : GiphyRepository {

    companion object {
        val cache = hashMapOf<String, ImagesModel>() // Instead of DAO, lol
    }

    override fun search(query: String,
                        limit: Int,
                        offset: Int): Flow<SearchModel> =
        flow {
            val result
                = giphyService.search(BuildConfig.API_KEY, query, limit, offset)
                ?: return@flow
            emit(result)
            result?.data?.forEach { data ->
                data?.images?.let {
                    cache[data.id] = data.images
                }
            }
        }

    override fun getImages(id: String): ImagesModel? {
        if (!cache.contains(id)) {
            return null
        }

        return cache[id]
    }
}
