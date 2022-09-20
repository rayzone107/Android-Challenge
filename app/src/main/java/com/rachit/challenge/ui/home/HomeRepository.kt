package com.rachit.challenge.ui.home

import com.rachit.challenge.data.TestApi
import com.rachit.challenge.data.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository class that fetches list of locations from the server/local database
 */
@Singleton
class HomeRepository @Inject constructor(
    private val api: TestApi
) {
    suspend fun getAllLocations(): List<Location> = withContext(Dispatchers.IO) {
        val locations = api.getLocations()
        locations
    }
}
