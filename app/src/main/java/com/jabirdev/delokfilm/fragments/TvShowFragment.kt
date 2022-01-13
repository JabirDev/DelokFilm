package com.jabirdev.delokfilm.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.activities.DetailActivity
import com.jabirdev.delokfilm.adapter.TvShowAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.databinding.FragmentTvShowBinding
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import com.jabirdev.delokfilm.utils.ObtainViewModel
import com.jabirdev.delokfilm.viewmodel.PopularTvViewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val adapter = TvShowAdapter()
    private var mainViewModel: PopularTvViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        mainViewModel = ObtainViewModel.popularTv(requireActivity() as AppCompatActivity)
        mainViewModel?.getPopularTv()?.observe(viewLifecycleOwner, { tv ->
            if (tv != null) adapter.setData(tv)
            binding.progressBar.visibility = View.GONE
        })

        binding.rvTvShow.adapter = adapter
        binding.rvTvShow.layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.rvTvShow.addItemDecoration(GridSpacingItemDecoration(spanCount, 16, true))

        adapter.addItemClickListener = TvShowAdapter.MovieItemClickListener { data, transition, viewLayout ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 300
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 300
            }
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), viewLayout, transition)
            val i = Intent(requireContext(), DetailActivity::class.java)
            i.putExtra(AppConstants.KEY_ID, data.id)
            i.putExtra(AppConstants.KEY_TITLE, data.originalName)
            i.putExtra(AppConstants.KEY_OVERVIEW, data.overview)
            i.putExtra(AppConstants.KEY_POSTER, "${BuildConfig.TMDB_IMAGE_URL}${data.posterPath}")
            i.putExtra(AppConstants.KEY_SCORE, data.voteAverage)
            i.putExtra(AppConstants.KEY_RATING, data.adult)
            i.putExtra(AppConstants.KEY_TRANSITION, transition)
            i.putExtra(AppConstants.KEY_TYPE, AppConstants.TYPE_TV)
            startActivity(i, options.toBundle())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

}