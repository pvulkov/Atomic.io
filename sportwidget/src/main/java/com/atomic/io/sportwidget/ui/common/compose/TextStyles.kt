package com.atomic.io.sportwidget.ui.common.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.atomic.io.sportwidget.ui.common.compose.ext.DefaultVerticalSpacer
import com.atomic.io.sportwidget.ui.theme.Purple40
import com.atomic.io.sportwidget.ui.theme.PurpleGrey40

val textStyle_H1 = TextStyle(
    fontSize = 20.sp,
    fontFamily = FontFamily.Default,
    color = Purple40,
    fontWeight = FontWeight.Medium
)

val textStyle_H2 = TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily.Default,
    color = PurpleGrey40,
    fontWeight = FontWeight.Medium
)

val textStyle_ErrorMessage = TextStyle(
    fontSize = 16.sp,
    fontFamily = FontFamily.Default,
    color = Color.Red,
    textAlign = TextAlign.Center
)

@Composable
@Preview
private fun TextStyles_Preview(){
    val text = "Lorem ipsum dolor sit amet, consectetur adipiscing"

    Column {

        Text(
            text = text,
            style = textStyle_H1,
        )

        DefaultVerticalSpacer()

        Text(
            text = text,
            style = textStyle_H1,
        )

        DefaultVerticalSpacer()

        Text(
            text = text,
            style = textStyle_ErrorMessage,
        )
    }
}
