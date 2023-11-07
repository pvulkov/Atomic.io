package com.atomic.io.sportwidget.data.repository

import com.atomic.io.sportwidget.data.model.Sport
import kotlinx.coroutines.delay

internal class ContentRepository {
    suspend fun getFeaturedSports(): List<Sport> {
        delay(5000)
        return Sport.createMockedSports()
    }
}
