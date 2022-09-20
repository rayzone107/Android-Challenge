package com.rachit.challenge.ui.detail

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
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Created by Rachit Goyal on 16/09/22.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val detailRepository = mock<DetailRepository>()
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        detailViewModel = DetailViewModel(detailRepository)
    }

    @Test
    fun `should have valid data`() = runTest {
        whenever(detailRepository.getLocationById(any())).thenReturn(getSingleLocation(1))
        detailViewModel = DetailViewModel(detailRepository)
        detailViewModel.getLocationById(1)
        runCurrent()
        Assert.assertEquals(
            1,
            detailViewModel.location.value?.data?.id
        )
    }

    @Test
    fun `should return error when null data returned`() = runTest {
        whenever(detailRepository.getLocationById(any())).thenReturn(null)
        detailViewModel = DetailViewModel(detailRepository)
        detailViewModel.getLocationById(1)
        runCurrent()
        Assert.assertEquals(
            R.string.error_data_not_available,
            detailViewModel.location.value?.message
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
}