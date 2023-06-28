package com.jozu.jetpack.compose.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jozu.jetpack.compose.tutorial.nav.Nav
import com.jozu.jetpack.compose.tutorial.screen.ConversationScreen
import com.jozu.jetpack.compose.tutorial.screen.TopScreen
import com.jozu.jetpack.compose.tutorial.ui.theme.ComposeTutorialTheme

/**
 * メインActivity
 * 画面と遷移を管理する
 *
 * Created by jozuko on 2023/06/28.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Nav.TopScreen.name) {
                    composable(route = Nav.TopScreen.name) {
                        TopScreen(
                            onNavigateToConversation = { navController.navigate(Nav.ConversationScreen.name) },
                        )
                    }
                    composable(route = Nav.ConversationScreen.name) { ConversationScreen() }
                }
            }
        }
    }
}
