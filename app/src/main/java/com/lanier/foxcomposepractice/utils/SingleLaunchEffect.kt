package com.lanier.foxcomposepractice.utils

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * Create by Eric
 * on 2022/5/6
 */
@Composable
fun SingleEffect(onClick: suspend () -> Unit) {
    var execute by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(execute) {
        if (execute) {
            onClick()
        }
        execute = false
    }
}
