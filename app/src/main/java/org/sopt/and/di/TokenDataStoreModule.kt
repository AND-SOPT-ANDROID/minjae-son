package org.sopt.and.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.local.TokenLocalDataSource
import org.sopt.and.data.local.TokenLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenLocalDataSourceModule {
    @Provides
    @Singleton
    fun provideTokenLocalDataSource(sharedPreferences: SharedPreferences): TokenLocalDataSource {
        return TokenLocalDataSourceImpl(sharedPreferences)
    }
}