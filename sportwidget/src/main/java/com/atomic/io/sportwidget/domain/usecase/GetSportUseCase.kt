package com.atomic.io.sportwidget.domain.usecase

import com.atomic.io.sportwidget.data.repository.ContentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetSportUseCase @Inject constructor(
    private val contentRepository: ContentRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke() = withContext(defaultDispatcher) {
        flow{
            val sport = contentRepository.getFeaturedSports().random()
            emit(sport)
        }
    }
}
