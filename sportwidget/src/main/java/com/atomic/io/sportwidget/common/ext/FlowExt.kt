package com.atomic.io.sportwidget.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

/**
 * Subscribes to a Flow by providing callbacks for handling the next item emitted, errors, and the completion of the flow.
 *
 * @param onNext A consumer function that will be called for each item emitted by the Flow.
 * @param onError A consumer function that will be called when an error occurs in the Flow.
 * @param onComplete An optional action to be invoked when the Flow completes successfully. If provided, it will be called
 * when the Flow completes without errors.
 */
internal suspend fun <T> Flow<T>.subscribe(
    onNext: Consumer1<T>,
    onError: Consumer1<Throwable>,
    onComplete: Action? = null
) {
    this
        .onEach { onNext(it) }
        .onCompletion { error: Throwable? ->
            if (error == null) {
                onComplete?.invoke()
            }
        }
        .catch { onError(it) }
        .collect()
}


/**
 * Returns a new [Flow] that, when collected, applies the specified [Consumer1] to each emitted item
 * while preserving the original item order and values. This operator is useful for performing side
 * effects on the emitted items without modifying the original Flow.
 *
 * @param [consumer1] The lambda to perform an action on each emitted item.
 * @return A new [Flow] that emits the original items and applies the [Consumer1] to each of them.
 */
internal fun <T> Flow<T>.peek(consumer1: Consumer1<T>): Flow<T> = flow {
    collect { value ->
        consumer1(value)
        emit(value)
    }
}
