package io.hanyoungpark.androidshowcase.repositories

import io.hanyoungpark.androidshowcase.models.SearchModel
import io.hanyoungpark.androidshowcase.services.GiphyService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
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
                = giphyService.search("", query, limit, offset)
                ?: return@flow
            emit(result)
        }
}