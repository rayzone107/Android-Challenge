package com.rachit.challenge.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rachit.challenge.R
import com.rachit.challenge.databinding.ActivityDetailBinding
import com.rachit.challenge.util.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        detailViewModel.getLocationById(intent.getIntExtra(EXTRA_LOCATION_ID, -1))
        observeUI()
    }

    private fun observeUI() {
        detailViewModel.location.observe(this) { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.let { location ->
                        binding.location = location
                        toggleError(false)
                        title = location.address.street
                    }
                }
                is Response.Error -> {
                    title = getString(R.string.error_title)
                    binding.tvError.setText(response.message ?: R.string.error_something_went_wrong)
                    toggleError(true)
                }
            }
        }
    }

    private fun toggleError(visible: Boolean) {
        binding.tvError.visibility = if (visible) View.VISIBLE else View.GONE
    }

    companion object {
        private const val EXTRA_LOCATION_ID = "EXTRA_LOCATION_ID"

        fun getIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_LOCATION_ID, id)
            return intent
        }
    }
}