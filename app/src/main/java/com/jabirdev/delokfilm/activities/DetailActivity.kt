package com.jabirdev.delokfilm.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import coil.load
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.databinding.ActivityDetailBinding
import com.jabirdev.delokfilm.models.DataModel
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.GridLayoutManager
import com.jabirdev.delokfilm.adapter.CastAdapter
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import kotlin.math.roundToInt


class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var dataMovie: DataModel.Data? = null
    private val adapter = CastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataMovie = intent.getParcelableExtra(AppConstants.KEY_MOVIE_DATA)
        val transition = intent.getStringExtra(AppConstants.KEY_TRANSITION)
        binding.root.transitionName = transition

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = ""
        binding.tvReleaseDate.text = dataMovie?.releaseDate
        binding.tvTitle.text = dataMovie?.title
        binding.tvOverview.text = dataMovie?.overview
        binding.progressScore.progress = (dataMovie?.userScore!! * 10).roundToInt()
        binding.tvUserScore.text = dataMovie?.userScore.toString()
        binding.tvRating.text = dataMovie?.rating
        val posterId = resources.getIdentifier(dataMovie?.poster, "drawable", packageName)
        binding.imageView2.load(posterId) {
            listener(
                onSuccess = { _, _ -> setUpSystemBars() }
            )
        }

        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showSystemUI()
            2
        } else {
            hideSystemUI()
            4
        }
        dataMovie?.casts?.let { adapter.setData(it) }
        binding.rvCasts.adapter = adapter
        binding.rvCasts.layoutManager = GridLayoutManager(this, spanCount)
        binding.rvCasts.addItemDecoration(GridSpacingItemDecoration(spanCount, 16, true))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.menu_share -> {
                val sb = StringBuilder()
                sb.append("Title: ${dataMovie?.title}\n\n")
                sb.append("Overview: \n${dataMovie?.overview}\n\n")
                sb.append("Casts: \n")
                dataMovie?.casts?.forEachIndexed { index, cast ->
                    sb.append("${index + 1}. ${cast.name} as ${cast.character}\n")
                }
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plain"
                i.putExtra(Intent.EXTRA_TEXT, sb.toString())
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, binding.root).show(systemBars())
    }

    private fun setUpSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding.imageView2.viewTreeObserver
            .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val measuredHeight: Int = binding.imageView2.measuredHeight
                    val lp: ViewGroup.LayoutParams = binding.collapsingToolbar.layoutParams
                    lp.height = measuredHeight
                    binding.collapsingToolbar.layoutParams = lp
                    binding.imageView2.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }

}