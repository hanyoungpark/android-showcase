package io.hanyoungpark.androidshowcase.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.viewmodels.DownloadViewModel

class DownloadActivity : AppCompatActivity() {
    private val downloadViewModel: DownloadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
    }
}