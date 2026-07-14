package com.vpvn.designsystem.accessibility

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription

/**
 * Adds a content description for accessibility services such as TalkBack.
 */
fun Modifier.accessibility(
    contentDescription: String
): Modifier = semantics {
    this.contentDescription = contentDescription
}

/**
 * Marks this composable as a heading.
 */
fun Modifier.accessibleHeading(): Modifier = semantics {
    heading()
}

/**
 * Marks this composable as a button.
 */
fun Modifier.accessibleButton(
    contentDescription: String
): Modifier = semantics {
    role = Role.Button
    this.contentDescription = contentDescription
}

/**
 * Adds semantics for toggleable components.
 */
fun Modifier.accessibleToggle(
    contentDescription: String,
    checked: Boolean
): Modifier = semantics {
    role = Role.Switch
    this.contentDescription = contentDescription
    stateDescription = if (checked) "On" else "Off"
}