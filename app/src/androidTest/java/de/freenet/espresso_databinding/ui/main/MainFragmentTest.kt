package de.freenet.espresso_databinding.ui.main

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockito_kotlin.whenever
import de.freenet.espresso_databinding.MainActivity
import de.freenet.espresso_databinding.R
import de.freenet.espresso_databinding.testutils.DataBindingIdlingResourceRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.mockito.Mockito.mock


@LargeTest
class MainFragmentTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @get:Rule
    val databindingIdlingResourceRule = DataBindingIdlingResourceRule(activityTestRule)

    private val mockViewModel: MainViewModel = mock(MainViewModel::class.java)
    private val mockedViewState = MutableLiveData<MainViewState>()

    @Before
    fun setUp() {
        whenever(mockViewModel.viewState).thenReturn(mockedViewState)

        loadKoinModules(module {
            viewModel { mockViewModel }
        })


        activityTestRule.launchActivity(Intent())

        activityTestRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testFragmentVisible() {
        onView(withId(R.id.itemList)).check(matches(isDisplayed()))
    }

    @Test
    fun testRenderingWorks() {
        mockedViewState.postValue(MainViewState.ItemsLoaded(listOf(MainItem("123", 1), MainItem("245", 2))))
        onView(withText("123")).check(matches(isDisplayed()))
        onView(withText("245")).check(matches(isDisplayed()))
    }


}