package io.hanyoungpark.androidshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hanyoungpark.androidshowcase.models.DataModel
import io.hanyoungpark.androidshowcase.repositories.GiphyRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
):ViewModel() {
    private val _images = MutableLiveData<DataModel>()
    val images:LiveData<DataModel>
        get() = _images

    fun loadImages(id: String) {
        viewModelScope.launch {
            val ret = giphyRepository.getImages(id)
            ret?.let {
                _images.postValue(it)
            }
        }
    }
}
