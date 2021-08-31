package io.hanyoungpark.androidshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Component
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hanyoungpark.androidshowcase.models.DataModel
import io.hanyoungpark.androidshowcase.modules.GiphyModule
import io.hanyoungpark.androidshowcase.repositories.GiphyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
) : ViewModel() {

    private val _searchResult = MutableLiveData<List<DataModel>>()
    val searchResult: LiveData<List<DataModel>>
        get() = _searchResult

    private val page = 0
    private val size = 50

    fun search(query: String) {
        viewModelScope.launch {
            val flow = giphyRepository.search(query, size, page)
            flow.collect {
                it.data?.let { list ->
                    _searchResult.postValue(list)
                }
            }
        }
    }
}
