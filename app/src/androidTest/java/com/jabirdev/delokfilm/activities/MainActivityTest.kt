package com.jabirdev.delokfilm.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.utils.GetJson
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyData = GetJson.getDummyData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadFragment(){
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeRight())
    }

    @Test
    fun changeTheme(){
        onView(withId(R.id.menu_day)).perform(click())
        onView(withId(R.id.menu_night)).perform(click())
    }

    @Test
    fun loadData(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyData.movies.size - 5))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyData.tvShows.size - 5))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).perform(click(), swipeUp())
        onView(withId(R.id.rv_casts)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_casts)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.menu_share)).perform(click())
    }

}