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
import com.jabirdev.delokfilm.activities.DetailActivity
import com.jabirdev.delokfilm.activities.MainViewModel
import com.jabirdev.delokfilm.adapter.MovieAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.databinding.FragmentTvShowBinding
import com.jabirdev.delokfilm.utils.GetJson
import com.jabirdev.delokfilm.utils.GridSpacingItemDecoration
import com.jabirdev.delokfilm.utils.ObtainViewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieAdapter()
    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        mainViewModel = ObtainViewModel.main(requireActivity() as AppCompatActivity)
        val json = GetJson.data(requireContext())
        val dataJson = mainViewModel?.getJson(json)
        adapter.setData(dataJson?.tvShows!!)

        binding.rvTvShow.adapter = adapter
        binding.rvTvShow.layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.rvTvShow.addItemDecoration(GridSpacingItemDecoration(spanCount, 16, true))

        adapter.addItemClickListener = MovieAdapter.MovieItemClickListener { data, transition, viewLayout ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 300
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 300
            }
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), viewLayout, transition)
            val i = Intent(requireContext(), DetailActivity::class.java)
            i.putExtra(AppConstants.KEY_MOVIE_DATA, data)
            i.putExtra(AppConstants.KEY_TRANSITION, transition)
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