package io.hanyoungpark.androidshowcase.repositories

import dagger.Component
import io.hanyoungpark.androidshowcase.models.SearchModel
import io.hanyoungpark.androidshowcase.modules.GiphyModule
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
                = giphyService.search("L1aHdsajAMSXlS2TAVaI8FNkEbeRWBzB", query, limit, offset)
                ?: return@flow
            emit(result)
        }
}
