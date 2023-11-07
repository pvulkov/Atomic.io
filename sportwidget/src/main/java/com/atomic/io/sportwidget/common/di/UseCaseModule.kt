package com.atomic.io.sportwidget.common.di

import com.atomic.io.sportwidget.data.repository.ContentRepository
import com.atomic.io.sportwidget.domain.usecase.GetSportUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    internal fun provideGetSportUseCase(
        contentRepository: ContentRepository,
    ): Lazy<GetSportUseCase> = lazy {
        GetSportUseCase(
            contentRepository = contentRepository,
            defaultDispatcher = Dispatchers.Default
        )
    }
}
