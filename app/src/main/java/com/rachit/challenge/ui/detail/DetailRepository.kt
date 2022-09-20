package com.rachit.challenge.ui.detail

import com.rachit.challenge.data.TestApi
import com.rachit.challenge.data.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository class that fetches location details from the server/local database
 */
@Singleton
class DetailRepository @Inject constructor(
    private val api: TestApi
) {
    suspend fun getLocationById(id: Int): Location? = withContext(Dispatchers.IO) {
        val location = api.getLocationById(id)
        location
    }
}
