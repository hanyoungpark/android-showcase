package io.hanyoungpark.androidshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hanyoungpark.androidshowcase.models.DataModel
import io.hanyoungpark.androidshowcase.repositories.GiphyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
) : ViewModel() {

    private val _searchResult = MutableLiveData<List<DataModel>>()
    val searchResult: LiveData<List<DataModel>>
        get() = _searchResult

    private var q = ""
    private var page = 0
    private var lastPage = 0
    private val size = 50
    private var lastQueryTime = 0L

    fun search(query: String) {
        q = query
        page = 0
        lastPage = 0
        lastQueryTime = 0L
        doSearch(q)
    }

    fun next() {
        if (page >= lastPage) {
            return
        }
        val current = System.currentTimeMillis() / 1000
        if (current - lastQueryTime < 5) {
            return
        }
        lastQueryTime = current
        page++
        doSearch(q)
    }

    private fun doSearch(query: String) {
        viewModelScope.launch {
            val flow = giphyRepository.search(query, size, page*size)
            flow.collect {
                it.data?.let { list ->
                    _searchResult.postValue(list)
                }
                it.pagination?.let p@ { p ->
                    if (p.count == 0 || p.total_count == 0) {
                        return@p
                    }
                    lastPage = ceil(p.total_count.toDouble()/p.count.toDouble()).toInt()
                    print("total page $lastPage")
                }
            }
        }
    }
}
