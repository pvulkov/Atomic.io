package com.atomic.io.sportwidget.common.ext


import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class FlowExtKtTest {

    @Test
    fun `Test Flow subscribe methods prints elements properly`() {
        val result = StringBuilder()
        runBlocking {
            flowOf(1, 2, 3)
                .subscribe(
                    { result.append("next $it, ") },
                    { result.append("error $it, ") },
                    { result.append("complete ") }
                )
        }

        val expected = "next 1, next 2, next 3, complete "
        assertEquals(expected, result.toString())
    }

    @Test
    fun `Test Flow subscribe methods catches exception`() {
        val result = StringBuilder()
        runBlocking {
            flow {
                emit(1)
                throw RuntimeException()
            }
                .subscribe(
                    { result.append("next $it, ") },
                    { result.append("error $it, ") },
                    { result.append("complete ") }
                )
        }

        val expected = "next 1, error java.lang.RuntimeException, "
        assertEquals(expected, result.toString())
    }


    /*
    * Note (pvalkov) that in this case we're MODIFYING a VALUE.
    * Since a values are always copied when passed as arguments peek operator WILL NOT CHANGE
    * the initial value down the stream
    */
    @Test
    fun `Test Peek operator should correctly 1`() {
        val result = StringBuilder()
        runBlocking {
            flow {
                emit(1)
            }
                .peek {
                    it * 2
                }
                .subscribe(
                    { result.append("next $it, ") },
                    { result.append("error $it, ") },
                    { result.append("complete ") }
                )
        }

        val expected = "next 1, complete "
        assertEquals(expected, result.toString())
    }

    /*
     * Note (pvalkov) that in this case we're MODIFYING a REFERENCE.
     * Since a reference point to the same place in memory peek operator WILL CHANGE
     * the actual reference value down the stream
     */
    @Test
    fun `Test Peek operator should correctly 2`() {
        val result = StringBuilder()
        runBlocking {
            flow {
                emit(AtomicInteger(0))
            }
                .peek {
                    it.set(5)
                }
                .subscribe(
                    { result.append("next ${it.get()}, ") },
                    { result.append("error $it, ") },
                    { result.append("complete ") }
                )
        }

        val expected = "next 5, complete "
        assertEquals(expected, result.toString())
    }

}