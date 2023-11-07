package com.atomic.io.sportwidget.ui.sport

import com.atomic.io.sportwidget.data.model.Sport
import com.atomic.io.sportwidget.data.presentation.DataPresentation
import kotlinx.coroutines.flow.StateFlow

interface SportWidgetVmContract {

    val dateStateFlow: StateFlow<DataPresentation<Sport>>

    fun onRefreshSportClicked()

}
