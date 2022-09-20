package com.rachit.challenge.ui.detail

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
 * ViewModel that posts single location data or error to the observable being observed in DetailActivity
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
) : ViewModel() {

    val location: MutableLiveData<Response<Location>> = MutableLiveData()

    fun getLocationById(id: Int) {
        viewModelScope.launch {
            val location = detailRepository.getLocationById(id)
            location?.let { this@DetailViewModel.location.postValue(Response.Success(it)) }
                ?: kotlin.run {
                    this@DetailViewModel.location.postValue(Response.Error(R.string.error_data_not_available))
                }
        }
    }
}