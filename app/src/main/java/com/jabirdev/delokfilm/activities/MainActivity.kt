package com.jabirdev.delokfilm.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.adapter.TabAdapter
import com.jabirdev.delokfilm.databinding.ActivityMainBinding
import com.jabirdev.delokfilm.fragments.MoviesFragment
import com.jabirdev.delokfilm.fragments.TvShowFragment
import com.jabirdev.delokfilm.utils.ThemeUtil
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val fragmentList = arrayListOf( MoviesFragment(), TvShowFragment() )
    private var themeUtil: ThemeUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        themeUtil = ThemeUtil(this)

        setSupportActionBar(binding.appBar.toolbar)

        val tabTitles = resources.getStringArray(R.array.tab_titles)
        val adapter = TabAdapter(supportFragmentManager, lifecycle)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter
        TabLayoutMediator (binding.mainTab, binding.viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        val darkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (themeUtil?.getActiveTheme() == AppCompatDelegate.MODE_NIGHT_YES ||
            darkMode == Configuration.UI_MODE_NIGHT_YES){
            menu[1].isVisible = false
        } else {
            menu[0].isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_day -> {
                themeUtil?.changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, getString(R.string.title_day), Toast.LENGTH_SHORT).show()
            }
            R.id.menu_night -> {
                themeUtil?.changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, getString(R.string.title_night), Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}