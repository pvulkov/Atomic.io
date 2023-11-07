package com.atomic.io.sportwidget.ui.sport.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atomic.io.sportwidget.R
import com.atomic.io.sportwidget.data.model.Sport
import com.atomic.io.sportwidget.data.presentation.DataPresentation
import com.atomic.io.sportwidget.ui.common.compose.ErrorWidget
import com.atomic.io.sportwidget.ui.common.compose.ext.DefaultVerticalSpacer
import com.atomic.io.sportwidget.ui.sport.SportWidgetViewModel
import com.atomic.io.sportwidget.ui.sport.SportWidgetVmContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SportWidget(
    modifier: Modifier = Modifier,
    // NOTE (pvalkov) default VM contract. Can be changed on runtime
    vmContract: SportWidgetVmContract = hiltViewModel<SportWidgetViewModel>(),
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        var presentationState: DataPresentation<Sport> by remember {
            mutableStateOf(
                DataPresentation.Initial
            )
        }
        presentationState = vmContract.dateStateFlow.collectAsState().value

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            item {
                SportHeaderWidget(
                    header = stringResource(id = R.string.featured_sport_text),
                    isLoading = presentationState is DataPresentation.Loading,
                    refreshAction = vmContract::onRefreshSportClicked
                )
            }

            with(presentationState) {
                when (this) {
                    is DataPresentation.Error -> renderErrorWidget(this.error)
                    is DataPresentation.Success -> renderDataWidget(this.presentation)
                    //NOTE (pvalkov) do nothing
                    is DataPresentation.Loading,
                    is DataPresentation.Initial -> Unit
                }
            }
        }
        DefaultVerticalSpacer()
    }
}

private fun LazyListScope.renderDataWidget(sport: Sport) = item {
    SportContentWidget(
        title = sport.name,
        description = sport.description,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
    )
}

private fun LazyListScope.renderErrorWidget(throwable: Throwable) = item {
    ErrorWidget(message = throwable.message)
}

@Composable
@Preview
private fun Preview_SportWidget() {
     SportWidget(
         vmContract = object : SportWidgetVmContract {

             override fun onRefreshSportClicked() {}
             override val dateStateFlow: StateFlow<DataPresentation<Sport>>
                 get() = MutableStateFlow(DataPresentation.Success(Sport.createMockedSports()[0]))
         }
     )
}