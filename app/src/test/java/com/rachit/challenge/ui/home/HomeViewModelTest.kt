package com.rachit.challenge.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rachit.challenge.R
import com.rachit.challenge.data.model.Address
import com.rachit.challenge.data.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Created by Rachit Goyal on 16/09/22.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val homeRepository = mock<HomeRepository>()
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun `should have non empty data`() = runTest {
        whenever(homeRepository.getAllLocations()).thenReturn(getListOfLocations(2))
        homeViewModel = HomeViewModel(homeRepository)
        homeViewModel.getAllLocations()
        runCurrent()
        Assert.assertEquals(
            2,
            homeViewModel.allLocations.value?.data?.size
        )
    }

    @Test
    fun `should return error when list is empty`() = runTest {
        whenever(homeRepository.getAllLocations()).thenReturn(getListOfLocations(0))
        homeViewModel = HomeViewModel(homeRepository)
        homeViewModel.getAllLocations()
        runCurrent()
        Assert.assertEquals(
            R.string.error_no_locations,
            homeViewModel.allLocations.value?.message
        )
    }

    @Test
    fun `should return error when exception thrown`() = runTest {
        whenever(homeRepository.getAllLocations()).thenThrow(IllegalStateException())
        homeViewModel = HomeViewModel(homeRepository)
        homeViewModel.getAllLocations()
        runCurrent()
        Assert.assertEquals(
            R.string.error_something_went_wrong,
            homeViewModel.allLocations.value?.message
        )
    }

    private fun getSingleLocation(id: Int) = Location(
        id = id,
        address = Address(
            street = "Street",
            city = "City",
            state = "State"
        ),
        description = "Description",
        distance = "Distance",
        locationPhoto = "Photo",
        price = 100
    )

    private fun getListOfLocations(count: Int): List<Location> {
        val locations = mutableListOf<Location>()
        for (i in 1..count) {
            locations.add(getSingleLocation(i))
        }
        return locations
    }
}