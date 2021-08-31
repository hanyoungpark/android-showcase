package io.hanyoungpark.androidshowcase.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.viewmodels.GiphyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val giphyViewModel:GiphyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        giphyViewModel.searchResult.observe(this, Observer {
            println("${it.count()}")
        })
        giphyViewModel.search("love")
    }
}
