package com.atomic.io.sportwidget.common.ext

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TypeExtKtTest {

    private var actionInvoked = false

    // An Action typealias representing a simple action.
    private val action: Action = { actionInvoked = true }

    @Before
    fun setUp() {
        // Reset the actionInvoked flag before each test.
        actionInvoked = false
    }


    @Test
    fun `Producer type alias should work correctly`() {
        val myProducer: Producer<String> = { "Hello, World!" }
        val result = myProducer()
        assertEquals("Hello, World!", result)
    }

    @Test
    fun `Consumer type alias should work correctly`() {
        var consumedValue: Int? = null
        val consumer: Consumer<Int> = { t ->
            consumedValue = t
        }
        consumer(42)
        assertEquals(consumedValue, 42)
    }

    @Test
    fun `Action type alias should work correctly`() {
        action.invoke()
        assertTrue(actionInvoked)
    }

}