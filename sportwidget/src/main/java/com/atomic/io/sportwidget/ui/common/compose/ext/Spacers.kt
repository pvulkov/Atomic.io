package com.atomic.io.sportwidget.ui.common.compose.ext

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp

@Composable
fun DefaultVerticalSpacer() {
    Spacer(modifier = Modifier
        .height(20.dp)
        .semantics { testTag = "Spacer20VerticalWidget" }
    )
}
