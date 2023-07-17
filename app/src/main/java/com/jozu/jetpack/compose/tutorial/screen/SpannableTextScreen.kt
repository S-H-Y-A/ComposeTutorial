package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.jozu.jetpack.compose.tutorial.screen.composable.LinkText
import com.jozu.jetpack.compose.tutorial.screen.composable.LinkTextData

/**
 *
 * Created by jozuko on 2023/07/17.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpannableTextScreen() {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LinkText(
                text = "GoogleもしくはYahooを開きます。",
                linkTextList = listOf(
                    LinkTextData("Google", "https://www.google.co.jp/"),
                    LinkTextData("Yahoo", "https://www.yahoo.co.jp"),
                ),
                modifier = Modifier.padding(8.dp),
                onClick = { url ->
                    uriHandler.openUri(url)
                },
            )
        }
    }
}