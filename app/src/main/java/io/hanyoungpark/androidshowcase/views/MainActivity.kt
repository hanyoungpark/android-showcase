package io.hanyoungpark.androidshowcase.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.viewmodels.GiphyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private val giphyViewModel:GiphyViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var searchResult: RecyclerView
    private lateinit var searchAdapter: GiphyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        bindViewModel()
    }

    private fun setupViews() {
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = GiphyDecoration(16)
        searchAdapter = GiphyAdapter(this.baseContext)
        searchResult = findViewById(R.id.searchResult)
        searchResult.adapter = searchAdapter
        searchResult.layoutManager = layoutManager
        searchResult.addItemDecoration(decoration)
        searchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                layoutManager.invalidateSpanAssignments()
                if (!recyclerView.canScrollVertically(1)) {
                    giphyViewModel.next()
                }
            }
        })

        progressBar = findViewById(R.id.progressBar)
    }

    private fun bindViewModel() {
        giphyViewModel.searchResult.observe(this, Observer {
            progressBar.visibility = View.GONE
            searchAdapter.add(it)
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
            progressBar.visibility = View.VISIBLE
            searchAdapter.reset()
            giphyViewModel.search(it)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
