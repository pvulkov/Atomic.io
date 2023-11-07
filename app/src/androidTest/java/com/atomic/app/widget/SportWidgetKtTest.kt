package com.atomic.app.widget

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.atomic.io.sportwidget.R
import com.atomic.io.sportwidget.data.model.Sport
import com.atomic.io.sportwidget.data.presentation.DataPresentation
import com.atomic.io.sportwidget.ui.sport.SportWidgetVmContract
import com.atomic.io.sportwidget.ui.sport.widget.SportWidget
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 *  Our lovely Sport widget have four internal states:
 *  1. Initial - [DataPresentation.Initial]
 *  2. Loading - [DataPresentation.Loading]
 *  3. Success - [DataPresentation.Success]
 *  4. Error - [DataPresentation.Error]
 *
 *  Here we test them all.
 *
 *  @see [DataPresentation]
 * */
@HiltAndroidTest
class SportWidgetKtTest {

    // NOTE (pvalkov) we don't really need hilt rule, because we're nto injecting anything
    // Let's keep it here in case that changes
    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val rule = createAndroidComposeRule<ComponentActivity>()


    // Sport widget Compose components
    private val sportHeaderWidget = hasTestTag("SportHeaderWidget")
    private val errorWidget = hasTestTag("ErrorWidget")
    private val sportContentWidget = hasTestTag("SportContentWidget")
    private val isRotatingFalse = hasTestTag("Modifier_rotateIf_is_false")
    private val isRotatingTrue = hasTestTag("Modifier_rotateIf_is_true")

    // VM-Contract
    private lateinit var vmContract: SportWidgetVmContract

    // DataPresentation flow
    private lateinit var _dateStateFlow: MutableStateFlow<DataPresentation<Sport>>

    // Counter we use to test onRefreshSportClicked action
    private val counter = AtomicInteger(0)

    @Before
    fun setUp() {
        hiltRule.inject()
        _dateStateFlow = MutableStateFlow(DataPresentation.Initial)
        vmContract = object : SportWidgetVmContract {
            override val dateStateFlow: StateFlow<DataPresentation<Sport>>
                get() = _dateStateFlow

            override fun onRefreshSportClicked() {
                counter.incrementAndGet()
            }
        }

        rule.setContent {
            SportWidget(
                vmContract = vmContract,
            )
        }
    }

    @Test
    fun test_sport_widget_initial_state() {
        runBlocking {
            _dateStateFlow.emit(DataPresentation.Initial)
        }
        rule.onNode(sportHeaderWidget).assertExists()
        rule.onNode(errorWidget).assertDoesNotExist()
        rule.onNode(sportContentWidget).assertDoesNotExist()
        rule.onNode(isRotatingFalse).assertExists()
        rule.onNode(isRotatingTrue).assertDoesNotExist()
    }

    @Test
    fun test_sport_widget_loading_state() {
        runBlocking {
            _dateStateFlow.emit(DataPresentation.Loading)
        }
        rule.onNode(sportHeaderWidget).assertExists()
        rule.onNode(errorWidget).assertDoesNotExist()
        rule.onNode(sportContentWidget).assertDoesNotExist()
        rule.onNode(isRotatingFalse).assertDoesNotExist()
        rule.onNode(isRotatingTrue).assertExists()
    }

    @Test
    fun test_sport_widget_error_state() {
        val exception = Exception("Dummy exception")
        runBlocking {
            _dateStateFlow.emit(DataPresentation.Error(exception))
        }

        val errorMessage: String =
            rule.activity.getString(R.string.error_placeholder_text, exception.message)

        rule.onNode(sportHeaderWidget).assertExists()
        rule.onNode(errorWidget).assertExists()
        rule.onNode(sportContentWidget).assertDoesNotExist()
        rule.onNode(isRotatingFalse).assertExists()
        rule.onNode(isRotatingTrue).assertDoesNotExist()
        rule.onNodeWithTag("ErrorWidgetText").assertTextEquals(errorMessage)
    }

    @Test
    fun test_sport_widget_success_state() {
        val aSport = Sport.createMockedSports().random()
        runBlocking {
            _dateStateFlow.emit(DataPresentation.Success(aSport))
        }

        rule.onNode(sportHeaderWidget).assertExists()
        rule.onNode(errorWidget).assertDoesNotExist()
        rule.onNode(sportContentWidget).assertExists()
        rule.onNode(isRotatingFalse).assertExists()
        rule.onNode(isRotatingTrue).assertDoesNotExist()

        rule.onAllNodesWithTag("TextTitle")[1].assertTextEquals(aSport.name)
        rule.onNodeWithTag("TextDescription").assertTextEquals(aSport.description)
    }

    @Test
    fun test_sport_widget_refresh_action_invoked() {
        rule.onNode(isRotatingFalse).assertExists()

        assertTrue(counter.get() == 0)
        rule.onNode(isRotatingFalse).performClick()
        assertTrue(counter.get() == 1)
        rule.onNode(isRotatingFalse).performClick()
        assertTrue(counter.get() == 2)
        rule.onNode(isRotatingFalse).performClick()
        assertTrue(counter.get() == 3)
    }
}