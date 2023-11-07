package com.atomic.io.sportwidget.ui.sport.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atomic.io.sportwidget.R
import com.atomic.io.sportwidget.common.ext.Action
import com.atomic.io.sportwidget.ui.common.compose.TextTitle
import com.atomic.io.sportwidget.ui.theme.Purple40
import com.atomic.io.sportwidget.ui.theme.PurpleGrey80
import com.emerchantpay.app.ui.common.compose.ext.onAction
import com.emerchantpay.app.ui.common.compose.ext.rotateIf

@Composable
fun SportHeaderWidget(
    header: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    refreshAction: Action? = null
) {
    Box(
        modifier = modifier.semantics { testTag = "SportHeaderWidget" }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(Purple40)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            TextTitle(
                modifier = Modifier.weight(1f),
                text = header,
                color = PurpleGrey80
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_refresh_24),
                contentDescription = "",
                tint = PurpleGrey80,
                modifier = Modifier
                    .size(24.dp)
                    .rotateIf(isLoading)
                    .onAction(refreshAction)
            )
        }
    }
}

@Composable
@Preview
private fun Preview_SportHeaderWidget() {
    SportHeaderWidget(
        header = stringResource(id = R.string.featured_sport_text)
    )
}
