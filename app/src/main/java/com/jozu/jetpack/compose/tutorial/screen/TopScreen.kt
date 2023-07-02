package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jozu.jetpack.compose.tutorial.screen.composable.IconButton
import com.jozu.jetpack.compose.tutorial.ui.theme.ComposeTutorialTheme

/**
 * 初期表示画面
 * 各画面へ遷移するボタンを持つ
 *
 * Created by jozuko on 2023/06/28.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@Composable
fun TopScreen(
    onNavigateToConversation: () -> Unit,
    onNavigateToMapFragment: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            IconButton(label = "Greeting", onClick = onNavigateToConversation)
            IconButton(label = "MapFragment", onClick = onNavigateToMapFragment)
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    ComposeTutorialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TopScreen(
                onNavigateToConversation = {},
                onNavigateToMapFragment = {},
            )
        }
    }
}
