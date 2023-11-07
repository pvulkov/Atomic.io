package com.atomic.io.sportwidget.common.ext


/**
 * A typealias representing a non-suspending function that produces a result of type [T].
 */
typealias Producer<T> = () -> T

/**
 * A typealias representing a suspending function that produces a result of type [T].
 */
typealias Producer1<T> = suspend () -> T

/**
 * A typealias representing a non-suspending function that consumes a value of type [T].
 */
typealias Consumer<T> = (t: T) -> Unit

/**
 * A typealias representing a suspending function that consumes a value of type [T].
 */
typealias Consumer1<T> = suspend (t: T) -> Unit

/**
 * A typealias representing a non-suspending function that performs an action with no parameters.
 */
typealias Action = () -> Unit

/**
 * A typealias representing a suspending function that performs an action with no parameters.
 */
typealias Action1 = suspend () -> Unit