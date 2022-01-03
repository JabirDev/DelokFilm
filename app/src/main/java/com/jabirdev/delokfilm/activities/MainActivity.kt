package com.jabirdev.delokfilm.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.adapter.TabAdapter
import com.jabirdev.delokfilm.databinding.ActivityMainBinding
import com.jabirdev.delokfilm.fragments.MoviesFragment
import com.jabirdev.delokfilm.fragments.TvShowFragment
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val fragmentList = arrayListOf<Fragment>( MoviesFragment(), TvShowFragment() )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabTitles = resources.getStringArray(R.array.tab_titles)
        val adapter = TabAdapter(supportFragmentManager, lifecycle)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter
        TabLayoutMediator (binding.mainTab, binding.viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

}