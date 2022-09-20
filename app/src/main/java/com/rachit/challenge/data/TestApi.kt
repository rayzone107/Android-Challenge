package com.rachit.challenge.data

import android.content.Context
import com.rachit.challenge.data.model.Location
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*

/**
 * API used to retrieve dummy data.
 * Uses Ktor with OkHttp
 *
 * @param context Context for MockClient
 * @see HttpClient
 */
class TestApi(context: Context) {
    companion object {
        private const val BASE_URL = "http://api.test.com/v1"
        private const val PHOTO_LOCATION = "//android_asset/"
    }

    private val client = HttpClient(OkHttp) {
        engine {
            addInterceptor(MockClient(context))
        }
        install(ContentNegotiation) { json() }
    }

    private val scope = context

    /**
     * Retrieve all available locations.
     * Suspended function for use with coroutines.
     *
     * @return List of Locations
     * @see Location
     */
    suspend fun getLocations(): List<Location> = client.endpointGet("/locations")
        .body<List<Location>>().map { location ->
            location.apply {
                locationPhoto = PHOTO_LOCATION + locationPhoto
            }
        }

    /**
     * Retrieve one Location by ID.
     * Suspended function for use with coroutines.
     *
     * @param locationId ID of location to retrieve
     * @return Location with matching ID. Null if no location found.
     * @see Location
     */
    suspend fun getLocationById(locationId: Int): Location? = getLocations().firstOrNull {
        it.id == locationId
    }

    private suspend fun HttpClient.endpointGet(endpoint: String) = this.get(BASE_URL + endpoint)
}
