package com.jabirdev.delokfilm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.adapter.TabAdapter
import com.jabirdev.delokfilm.app.AppConstants
import com.jabirdev.delokfilm.databinding.ActivityFavoriteBinding
import com.jabirdev.delokfilm.fragments.MoviesFragment
import com.jabirdev.delokfilm.fragments.TvShowFragment

class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.appBar?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tabTitles = resources.getStringArray(R.array.tab_titles)
        val adapter = TabAdapter(supportFragmentManager, lifecycle)

        adapter.fragmentList = setupFragment()
        binding?.viewPager?.adapter = adapter
        binding?.mainTab?.let {
            binding?.viewPager?.let { it1 ->
                TabLayoutMediator (it, it1){ tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupFragment(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>()
        val bundle = Bundle()
        bundle.putString(AppConstants.KEY_TYPE, AppConstants.TYPE_FAVORITE)
        val movie = MoviesFragment()
        movie.arguments = bundle
        val tv = TvShowFragment()
        tv.arguments = bundle

        fragments.add(movie)
        fragments.add(tv)
        return fragments
    }
}