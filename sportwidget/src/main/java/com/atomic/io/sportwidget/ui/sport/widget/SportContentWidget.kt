package com.atomic.io.sportwidget.ui.sport.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.atomic.io.sportwidget.ui.common.compose.TextDescription
import com.atomic.io.sportwidget.ui.common.compose.TextTitle
import com.atomic.io.sportwidget.ui.common.compose.ext.DefaultVerticalSpacer

@Composable
fun SportContentWidget(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .semantics { testTag = "SportContentWidget" }
    ) {

        TextTitle(text = title)
        DefaultVerticalSpacer()
        TextDescription(text = description)
    }
}

@Composable
@Preview
private fun Preview_SportContentWidget(){
    SportContentWidget(
        title = "Cool Sport",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    )
}
