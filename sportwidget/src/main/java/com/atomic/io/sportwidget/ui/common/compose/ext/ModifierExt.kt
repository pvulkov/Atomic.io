package com.emerchantpay.app.ui.common.compose.ext

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.atomic.io.sportwidget.common.ext.Action

/**
 * Extension function for Modifiers to apply a click action when the provided action is not null.
 *
 * @param action The action to be executed when the Composable is clicked. It can be null.
 * @return A Modifier that includes click functionality if the action is provided, or the original Modifier if the action is null.
 */
fun Modifier.onAction(action: Action?) = composed {
    action?.let {
        this.clickable(
            interactionSource = MutableInteractionSource(),
            indication = rememberRipple(bounded = true),
            onClick = { it.invoke() }
        )
    } ?: this
}

/**
 * An extension function that applies a rotation modifier to a Composable element based on the provided
 * boolean flag [condition]. If [condition] is true, it continuously rotates the element. If [condition]
 * is false, it keeps the element's rotation angle at 0 degrees.
 *
 * @param condition A boolean flag indicating whether the element should be continuously rotated.
 *
 * @return A Composable modifier that applies rotation based on the [condition] flag.
 */
fun Modifier.rotateIf(condition: Boolean) = composed {

    val infiniteTransition = rememberInfiniteTransition(label = "rotating")
    val angle by if (condition) {
        infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing)
            ), label = "rotating"
        )
    } else {
        remember { mutableFloatStateOf(0f) }
    }
    this.rotate(angle).semantics { testTag = "Modifier_rotateIf_is_$condition" }
}
