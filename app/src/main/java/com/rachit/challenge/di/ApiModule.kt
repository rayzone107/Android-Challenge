package com.rachit.challenge.di

import android.content.Context
import com.rachit.challenge.data.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * API Module defines for Dependency Injection using Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context): TestApi {
        return TestApi(context)
    }
}