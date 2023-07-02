package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *
 * Created by jozuko on 2023/07/02.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleMapsScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) { }
        },
    )
}