package com.jabirdev.delokfilm.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.activities.DetailActivity
import com.jabirdev.delokfilm.adapter.MovieAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.databinding.FragmentMoviesBinding
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import com.jabirdev.delokfilm.utils.ObtainViewModel
import com.jabirdev.delokfilm.viewmodel.PopularMovieViewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieAdapter()
    private var movieViewModel: PopularMovieViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        movieViewModel = ObtainViewModel.popularMovie(requireActivity() as AppCompatActivity)
        movieViewModel?.getPopularMovies()?.observe(viewLifecycleOwner, {
            if (it != null) adapter.setData(it)
            binding.progressBar.visibility = View.GONE
        })

        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.rvMovies.addItemDecoration(GridSpacingItemDecoration(spanCount, 16, true))

        adapter.addItemClickListener = MovieAdapter.MovieItemClickListener { data, transition, viewLayout ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 300
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 300
            }
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), viewLayout, transition)
            val i = Intent(requireContext(), DetailActivity::class.java)
            i.putExtra(AppConstants.KEY_ID, data.id)
            i.putExtra(AppConstants.KEY_TITLE, data.title)
            i.putExtra(AppConstants.KEY_OVERVIEW, data.overview)
            i.putExtra(AppConstants.KEY_POSTER, "${BuildConfig.TMDB_IMAGE_URL}${data.posterPath}")
            i.putExtra(AppConstants.KEY_SCORE, data.voteAverage)
            i.putExtra(AppConstants.KEY_RELEASE_DATE, data.releaseDate)
            i.putExtra(AppConstants.KEY_RATING, data.adult)
            i.putExtra(AppConstants.KEY_TRANSITION, transition)
            i.putExtra(AppConstants.KEY_TYPE, AppConstants.TYPE_MOVIE)
            startActivity(i, options.toBundle())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

}