package com.atomic.io.sportwidget.ui.sport

import androidx.lifecycle.ViewModel
import com.atomic.io.sportwidget.common.ext.launchInCurrentScope
import com.atomic.io.sportwidget.common.ext.subscribe
import com.atomic.io.sportwidget.data.model.Sport
import com.atomic.io.sportwidget.data.presentation.DataPresentation
import com.atomic.io.sportwidget.domain.usecase.GetSportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
internal class SportWidgetViewModel @Inject constructor(
    private val getSportUseCase: Lazy<GetSportUseCase>
) : ViewModel(), SportWidgetVmContract {

    private val _dateStateFlow = MutableStateFlow<DataPresentation<Sport>>(DataPresentation.Initial)
    override val dateStateFlow = _dateStateFlow

    init {
        fetchRandomSport()
    }

    override fun onRefreshSportClicked() {
        fetchRandomSport()
    }

    private fun fetchRandomSport() = launchInCurrentScope {
        getSportUseCase.value.invoke()
            .onStart { _dateStateFlow.emit(DataPresentation.Loading) }
            .subscribe(
                onNext = {
                    _dateStateFlow.emit(DataPresentation.Success(it))
                },
                onError = {
                    _dateStateFlow.emit(DataPresentation.Error(it))
                }
            )
    }

}
