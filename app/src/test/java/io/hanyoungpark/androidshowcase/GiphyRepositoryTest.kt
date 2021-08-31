package io.hanyoungpark.androidshowcase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.hanyoungpark.androidshowcase.models.SearchModel
import io.hanyoungpark.androidshowcase.repositories.GiphyRepositoryImpl
import io.hanyoungpark.androidshowcase.services.GiphyService
import io.hanyoungpark.androidshowcase.utils.TestCoroutineScopeRule
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GiphyRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScope = TestCoroutineScopeRule()

    @Mock
    private lateinit var giphyService: GiphyService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun verifyGiphyRepository(): Unit = runBlocking {
        `when`(giphyService.search("", "love", 25, 0))
            .thenReturn(SearchModel(null));
        val target = GiphyRepositoryImpl(giphyService)
        val flow = target.search("love", 25, 0)
        flow.collect {
        }
        `verify`(giphyService).search("", "love", 25, 0)
    }
}
