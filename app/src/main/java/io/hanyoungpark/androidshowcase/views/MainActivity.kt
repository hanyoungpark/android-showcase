package io.hanyoungpark.androidshowcase.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.viewmodels.GiphyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private val giphyViewModel:GiphyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        giphyViewModel.searchResult.observe(this, Observer {
            println("${it.count()}")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search) ?: return true
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            giphyViewModel.search(it)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
