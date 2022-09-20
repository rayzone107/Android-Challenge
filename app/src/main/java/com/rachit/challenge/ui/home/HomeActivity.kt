package com.rachit.challenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachit.challenge.R
import com.rachit.challenge.databinding.ActivityHomeBinding
import com.rachit.challenge.ui.detail.DetailActivity
import com.rachit.challenge.ui.home.adapter.LocationClickListener
import com.rachit.challenge.ui.home.adapter.LocationsAdapter
import com.rachit.challenge.util.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), LocationClickListener {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val locationsAdapter: LocationsAdapter by lazy { LocationsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        title = getString(R.string.locations)

        binding.apply {
            rvLocations.adapter = locationsAdapter
            rvLocations.addItemDecoration(
                DividerItemDecoration(
                    this@HomeActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        homeViewModel.getAllLocations()
        observeUI()
    }

    private fun observeUI() {
        homeViewModel.allLocations.observe(this) { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        locationsAdapter.setLocations(it)
                        toggleError(false)
                    }
                }
                is Response.Error -> {
                    toggleError(true)
                    binding.tvError.setText(response.message ?: R.string.error_something_went_wrong)
                }
            }
        }
    }

    private fun toggleError(visible: Boolean) {
        binding.apply {
            if (visible) {
                rvLocations.visibility = View.GONE
                tvError.visibility = View.VISIBLE
            } else {
                rvLocations.visibility = View.VISIBLE
                tvError.visibility = View.GONE
            }
        }
    }

    override fun clickOnLocation(locationId: Int) {
        startActivity(DetailActivity.getIntent(this, locationId))
    }
}
