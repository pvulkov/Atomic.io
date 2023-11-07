package com.atomic.io.sportwidget.ui.common.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.atomic.io.sportwidget.common.ext.Action
import com.atomic.io.sportwidget.ui.theme.Purple40
import com.emerchantpay.app.ui.common.compose.ext.onAction

@Composable
fun TextTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Purple40,
    action: Action? = null
) {
    Text(
        text = text,
        style = textStyle_H1,
        color = color,
        modifier = modifier
            .onAction(action)
            .semantics { testTag = "TextTitle" }
    )
}

@Preview
@Composable
private fun Preview_TextHeading1() {
    TextTitle(
        text = "Lorem Ipsum..."
    )
}
