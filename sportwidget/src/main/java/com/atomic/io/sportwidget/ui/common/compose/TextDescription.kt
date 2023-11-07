package com.atomic.io.sportwidget.ui.common.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = textStyle_H2,
        modifier = modifier.semantics { testTag = "TextDescription" }

    )
}

@Preview
@Composable
private fun Preview_TextDescription() {
    TextTitle(
        text = "Lorem Ipsum..."
    )
}
