package com.atomic.io.sportwidget.ui.common.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atomic.io.sportwidget.R

@Composable
fun ErrorWidget(
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .semantics { testTag = "ErrorWidget" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.semantics { testTag = "ErrorWidgetText" },
            text = stringResource(id = R.string.error_placeholder_text, message ?: ""),
            style = textStyle_ErrorMessage
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_ErrorWidget() {
    ErrorWidget(
        message = stringResource(id = R.string.error_placeholder_text, ""),
        modifier = Modifier
    )
}
