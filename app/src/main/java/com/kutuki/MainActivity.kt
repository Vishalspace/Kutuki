package com.kutuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.kutuki.databinding.ActivityMainBinding
import com.kutuki.model.Category
import com.kutuki.utils.Logger
import com.kutuki.service.VideoService
import com.kutuki.ui.CategoriesAdapter
import com.kutuki.utils.addTo
import com.kutuki.utils.injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var videoService: VideoService
    private lateinit var adapter: CategoriesAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector().inject(this)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        initUi(binding)
    }

    private fun initUi(binding: ActivityMainBinding) {
        adapter = CategoriesAdapter(applicationContext) { startVideosActivity(it.second) }
        binding.categoriesRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        videoService.getCategories().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { adapter.updateData(it) },
                { logger.error("Error fetching categories", it) }
            ).addTo(compositeDisposable)
    }

    fun startVideosActivity(category: Category) {
        val intent = VideosActivity.getIntent(this, category)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    companion object {
        private val logger = Logger(MainActivity::class.java.name)
    }
}