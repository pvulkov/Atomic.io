package com.atomic.io.sportwidget.common.di

import com.atomic.io.sportwidget.data.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    internal fun providesHttpLoggingInterceptor() = ContentRepository()

}