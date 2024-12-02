package org.sopt.and.di

import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.local.datasource.TokenLocalDataSource
import org.sopt.and.data.local.datasourceimpl.TokenLocalDataSourceImpl
import org.sopt.and.data.remote.datasource.AuthRemoteDataSource
import org.sopt.and.data.remote.datasource.UserRemoteDataSource
import org.sopt.and.data.remote.datasourceimpl.AuthRemoteDataSourceImpl
import org.sopt.and.data.remote.datasourceimpl.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindTokenLocalDataSource(
        tokenLocalDataSourceImpl: TokenLocalDataSourceImpl
    ): TokenLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}