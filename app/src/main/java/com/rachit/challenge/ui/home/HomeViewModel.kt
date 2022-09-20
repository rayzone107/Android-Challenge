package com.rachit.challenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachit.challenge.R
import com.rachit.challenge.data.model.Location
import com.rachit.challenge.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel that posts location list or error to the observable being observed in HomeActivity
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    val allLocations: MutableLiveData<Response<List<Location>>> = MutableLiveData()

    fun getAllLocations() {
        viewModelScope.launch {
            try {
                if (homeRepository.getAllLocations().isNotEmpty()) {
                    allLocations.postValue(Response.Success(homeRepository.getAllLocations()))
                } else {
                    allLocations.postValue(Response.Error(R.string.error_no_locations))
                }
            } catch (ex: Exception) {
                allLocations.postValue(Response.Error(R.string.error_something_went_wrong))
            }
        }
    }
}
