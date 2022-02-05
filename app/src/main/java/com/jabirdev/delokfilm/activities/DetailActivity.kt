package com.jabirdev.delokfilm.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.adapter.CastAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.app.AppConstants.KEY_ID
import com.jabirdev.delokfilm.app.AppConstants.KEY_TYPE
import com.jabirdev.delokfilm.app.AppConstants.TYPE_MOVIE
import com.jabirdev.delokfilm.data.source.local.entity.CreditsEntity
import com.jabirdev.delokfilm.databinding.ActivityDetailBinding
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import com.jabirdev.delokfilm.utils.ObtainViewModel
import com.jabirdev.delokfilm.viewmodel.DetailMovieViewModel
import com.jabirdev.delokfilm.viewmodel.DetailTvViewModel
import com.jabirdev.delokfilm.vo.Status
import kotlin.math.roundToInt


class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private var id: Int? = null
    private var mTitle: String? = null
    private var overview: String? = null
    private val casts = ArrayList<CreditsEntity>()
    private val adapter = CastAdapter()
    private var movieViewModel: DetailMovieViewModel? = null
    private var tvViewModel: DetailTvViewModel? = null
    private var isFavorite = false
    private var toolbarMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        movieViewModel = ObtainViewModel.detailMovie(this)
        tvViewModel = ObtainViewModel.detailTv(this)

        val type = intent.getStringExtra(KEY_TYPE)

        id = intent.getIntExtra(KEY_ID, -1)
        val transition = intent.getStringExtra(AppConstants.KEY_TRANSITION)
        binding?.root?.transitionName = transition

        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = ""

        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            showSystemUI()
            2
        } else {
//            hideSystemUI()
            4
        }

        type?.let {
            if (it == TYPE_MOVIE){
                movieViewModel?.setSelectedMovie(id.toString())
                movieViewModel?.moviesWithCredits?.observe(this, { movieWithCredit ->
                    if (movieWithCredit != null){
                        when(movieWithCredit.status){
                            Status.LOADING -> binding?.progressScore?.isIndeterminate = true
                            Status.SUCCESS -> {
                                if (movieWithCredit.data != null){
                                    binding?.progressScore?.isIndeterminate = false
                                    mTitle = movieWithCredit.data.movieEntity.title
                                    overview = movieWithCredit.data.movieEntity.overview
                                    isFavorite = movieWithCredit.data.movieEntity.favorite
                                    val poster = "${BuildConfig.TMDB_IMAGE_URL}${movieWithCredit.data.movieEntity.posterPath}"

                                    binding?.tvReleaseDate?.text = movieWithCredit.data.movieEntity.releaseDate
                                    binding?.tvTitle?.text = mTitle
                                    binding?.tvOverview?.text = overview
                                    binding?.progressScore?.progress = (movieWithCredit.data.movieEntity.voteAverage * 10).roundToInt()
                                    binding?.tvUserScore?.text = movieWithCredit.data.movieEntity.voteAverage.toString()
                                    binding?.tvRating?.text = movieWithCredit.data.movieEntity.popularity.toString()
                                    binding?.imagePoster?.load(poster) {
                                        listener(
                                            onSuccess = { _, _ -> setUpSystemBars() }
                                        )
                                    }

                                }
                            }
                            Status.ERROR -> {
                                binding?.progressScore?.isIndeterminate = false
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
                movieViewModel?.credits?.observe(this, { credits ->
                    if (credits != null){
                        when(credits.status){
                            Status.LOADING -> binding?.progressBar2?.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding?.progressBar2?.visibility = View.GONE
                                if (credits.data != null){
                                    casts.addAll(credits.data)
                                    adapter.setData(credits.data)
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressBar2?.visibility = View.GONE
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            } else {
                tvViewModel?.setSelectedTv(id.toString())
                tvViewModel?.tvWithCredits?.observe(this, { tvWithCredit ->
                    if (tvWithCredit != null){
                        when(tvWithCredit.status){
                            Status.LOADING -> binding?.progressScore?.isIndeterminate = true
                            Status.SUCCESS -> {
                                if (tvWithCredit.data != null){
                                    binding?.progressScore?.isIndeterminate = false
                                    mTitle = tvWithCredit.data.tvEntity.name
                                    overview = tvWithCredit.data.tvEntity.overview
                                    isFavorite = tvWithCredit.data.tvEntity.favorite
                                    val poster = "${BuildConfig.TMDB_IMAGE_URL}${tvWithCredit.data.tvEntity.posterPath}"

                                    binding?.tvReleaseDate?.text = tvWithCredit.data.tvEntity.firstAirDate
                                    binding?.tvTitle?.text = mTitle
                                    binding?.tvOverview?.text = overview
                                    binding?.progressScore?.progress = (tvWithCredit.data.tvEntity.voteAverage * 10).roundToInt()
                                    binding?.tvUserScore?.text = tvWithCredit.data.tvEntity.voteAverage.toString()
                                    binding?.tvRating?.text = tvWithCredit.data.tvEntity.popularity.toString()
                                    binding?.imagePoster?.load(poster) {
                                        listener(
                                            onSuccess = { _, _ -> setUpSystemBars() }
                                        )
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressScore?.isIndeterminate = false
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
                tvViewModel?.credits?.observe(this, { credits ->
                    if (credits != null){
                        when(credits.status){
                            Status.LOADING -> binding?.progressBar2?.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding?.progressBar2?.visibility = View.GONE
                                if (credits.data != null){
                                    credits.data.forEach { c ->
                                        val cr = CreditsEntity(
                                            c.creditId, c.tvId, c.name, c.profilePath, c.character
                                        )
                                        casts.add(cr)
                                    }
                                    adapter.setData(casts)
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressBar2?.visibility = View.GONE
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
        binding?.rvCasts?.adapter = adapter
        binding?.rvCasts?.layoutManager = GridLayoutManager(this, spanCount)
        binding?.rvCasts?.addItemDecoration(GridSpacingItemDecoration(spanCount, 16, true))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu, menu)
        toolbarMenu = menu
        if (isFavorite){
            menu[1].isVisible = false
            menu[2].isVisible = true
        } else {
            menu[1].isVisible = true
            menu[2].isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val type = intent.getStringExtra(KEY_TYPE)
        when (item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.menu_add_favorite -> {
                type?.let {
                    if (it == TYPE_MOVIE){
                        movieViewModel?.setFavorite()
                    } else {
                        tvViewModel?.setFavorite()
                    }
                }
                item.isVisible = false
                toolbarMenu?.getItem(2)?.isVisible = true
                Toast.makeText(this, "$mTitle added to favorite", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_remove_favorite -> {
                type?.let {
                    if (it == TYPE_MOVIE){
                        movieViewModel?.setFavorite()
                    } else {
                        tvViewModel?.setFavorite()
                    }
                }
                item.isVisible = false
                toolbarMenu?.getItem(1)?.isVisible = true
                Toast.makeText(this, "$mTitle removed from favorite", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_share -> {
                val sb = StringBuilder()
                sb.append("Title: ${title}\n\n")
                sb.append("Overview: \n${overview}\n\n")
                sb.append("Casts: \n")
                casts.forEachIndexed { index, cast ->
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

    private fun setUpSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding?.imagePoster?.viewTreeObserver
            ?.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val measuredHeight: Int? = binding?.imagePoster?.measuredHeight
                    val lp: ViewGroup.LayoutParams? = binding?.collapsingToolbar?.layoutParams
                    lp?.height = measuredHeight
                    binding?.collapsingToolbar?.layoutParams = lp
                    binding?.imagePoster?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }
            })
    }

}