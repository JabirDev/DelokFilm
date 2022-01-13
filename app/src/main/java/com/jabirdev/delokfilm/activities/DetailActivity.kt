package com.jabirdev.delokfilm.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.adapter.CastAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.app.AppConstants.KEY_ID
import com.jabirdev.delokfilm.app.AppConstants.KEY_OVERVIEW
import com.jabirdev.delokfilm.app.AppConstants.KEY_POSTER
import com.jabirdev.delokfilm.app.AppConstants.KEY_RATING
import com.jabirdev.delokfilm.app.AppConstants.KEY_RELEASE_DATE
import com.jabirdev.delokfilm.app.AppConstants.KEY_SCORE
import com.jabirdev.delokfilm.app.AppConstants.KEY_TITLE
import com.jabirdev.delokfilm.app.AppConstants.KEY_TYPE
import com.jabirdev.delokfilm.app.AppConstants.TYPE_MOVIE
import com.jabirdev.delokfilm.databinding.ActivityDetailBinding
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import com.jabirdev.delokfilm.utils.ObtainViewModel
import com.jabirdev.delokfilm.viewmodel.DetailMovieViewModel
import com.jabirdev.delokfilm.viewmodel.DetailTvViewModel
import kotlin.math.roundToInt


class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var id: Int? = null
    private var title: String? = null
    private var overview: String? = null
    private var poster: String? = null
    private var score: Float? = null
    private var releaseDate: String? = null
    private var rating: Boolean? = null
    private val adapter = CastAdapter()
    private var movieViewModel: DetailMovieViewModel? = null
    private var tvViewModel: DetailTvViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ObtainViewModel.detailMovie(this)
        tvViewModel = ObtainViewModel.detailTv(this)

        val type = intent.getStringExtra(KEY_TYPE)

        id = intent.getIntExtra(KEY_ID, -1)
        title = intent.getStringExtra(KEY_TITLE)
        overview = intent.getStringExtra(KEY_OVERVIEW)
        poster = intent.getStringExtra(KEY_POSTER)
        score = intent.getFloatExtra(KEY_SCORE,0F)
        releaseDate = intent.getStringExtra(KEY_RELEASE_DATE)
        rating = intent.getBooleanExtra(KEY_RATING, false)
        val transition = intent.getStringExtra(AppConstants.KEY_TRANSITION)
        binding.root.transitionName = transition

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = ""
        binding.tvReleaseDate.text = releaseDate
        binding.tvTitle.text = title
        binding.tvOverview.text = overview
        binding.progressScore.progress = (score!! * 10).roundToInt()
        binding.tvUserScore.text = score.toString()
        binding.tvRating.text = rating.toString()
        binding.imagePoster.load(poster) {
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

        type?.let {
            if (it == TYPE_MOVIE){
                movieViewModel?.getCredits(id.toString())?.observe(this, { castList ->
                    adapter.setData(castList)
                })
            } else {
                tvViewModel?.getDetailTv(id.toString())?.observe(this, { d ->
                    binding.tvReleaseDate.text = d.firstAirDate
                })
                tvViewModel?.getCredits(id.toString())?.observe(this, { castList ->
                    adapter.setData(castList)
                })
            }
        }
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
                sb.append("Title: ${title}\n\n")
                sb.append("Overview: \n${overview}\n\n")
                sb.append("Casts: \n")
//                dataMovie?.casts?.forEachIndexed { index, cast ->
//                    sb.append("${index + 1}. ${cast.name} as ${cast.character}\n")
//                }
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
        binding.imagePoster.viewTreeObserver
            .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val measuredHeight: Int = binding.imagePoster.measuredHeight
                    val lp: ViewGroup.LayoutParams = binding.collapsingToolbar.layoutParams
                    lp.height = measuredHeight
                    binding.collapsingToolbar.layoutParams = lp
                    binding.imagePoster.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }

}