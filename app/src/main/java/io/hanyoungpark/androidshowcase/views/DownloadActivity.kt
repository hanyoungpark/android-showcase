package io.hanyoungpark.androidshowcase.views

import android.content.ContentValues
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.viewmodels.DownloadViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.security.Permissions
import java.util.jar.Manifest
import android.app.DownloadManager
import android.content.Context


@AndroidEntryPoint
class DownloadActivity : AppCompatActivity() {
    private val downloadViewModel: DownloadViewModel by viewModels()
    private lateinit var titleTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var downloadButton: Button
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        setupViews()
        bindViewModel()
        val imageId = intent.getStringExtra("id") ?: return
        downloadViewModel.loadImages(imageId)
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (!granted) {
                    return@registerForActivityResult
                }
            }
    }

    override fun onStop() {
        super.onStop()
        permissionLauncher.unregister()
    }

    fun downloadButtonClicked(sender: View) {
        imageUrl?.let {
            downloadToLibrary(it)
        }
    }

    fun cancelButtonClicked(sender: View) {
        finish()
    }

    private fun bindViewModel() {
        downloadViewModel.images.observe(this, Observer {
            titleTextView.text = it.title
            it.images?.original?.url?.let { url ->
                Glide.with(this).load(url).into(imageView)
                imageUrl = url
                downloadButton.isActivated = true
            }
        })
    }

    private fun setupViews() {
        titleTextView = findViewById(R.id.titleTextView)
        imageView = findViewById(R.id.imageView)
        downloadButton = findViewById(R.id.downloadButton)
        downloadButton.isActivated = false
    }

    private fun downloadToLibrary(url: String) {
        when (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            PackageManager.PERMISSION_GRANTED -> {
                val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val uri =
                    Uri.parse(url)
                val request = DownloadManager.Request(uri)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.lastPathSegment)
                manager.enqueue(request)
                val alert = AlertDialog.Builder(this)
                alert
                    .setMessage(R.string.download_completed)
                    .setPositiveButton(R.string.okay, DialogInterface.OnClickListener { _, _ -> })
                alert.show()
            }
            else -> {
                val alert = AlertDialog.Builder(this)
                alert
                    .setMessage(R.string.ask_allow_permission)
                    .setPositiveButton(R.string.okay, DialogInterface.OnClickListener { _, _ ->
                        permissionLauncher.launch("android.permission.WRITE_EXTERNAL_STORAGE")
                    })
                alert.show()
            }
        }

    }
}
