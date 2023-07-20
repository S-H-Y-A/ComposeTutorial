package com.jozu.jetpack.compose.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jozu.jetpack.compose.tutorial.nav.Nav
import com.jozu.jetpack.compose.tutorial.nav.NavAnim
import com.jozu.jetpack.compose.tutorial.screen.ButtonSampleScreen
import com.jozu.jetpack.compose.tutorial.screen.ConversationScreen
import com.jozu.jetpack.compose.tutorial.screen.FlowTestScreen
import com.jozu.jetpack.compose.tutorial.screen.GoogleMapsScreen
import com.jozu.jetpack.compose.tutorial.screen.TextSampleScreen
import com.jozu.jetpack.compose.tutorial.screen.TopScreen
import com.jozu.jetpack.compose.tutorial.ui.theme.ComposeTutorialTheme
import java.net.URLDecoder
import java.net.URLEncoder

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
                    topScreen(navController = navController)
                    conversationScreen(navController = navController)
                    googleMapsScreen(navController = navController)
                    buttonSampleScreen(navController = navController)
                    textSampleScreen(navController = navController)
                    flowTestScreen(navController = navController)
                }
            }
        }
    }

    private fun NavGraphBuilder.topScreen(navController: NavHostController) {
        composable(
            route = Nav.TopScreen.name,
            enterTransition = NavAnim.enterTransition,
            exitTransition = NavAnim.exitTransition,
            popEnterTransition = NavAnim.popEnterTransition,
            popExitTransition = NavAnim.popExitTransition,
        ) {
            val title = URLEncoder.encode("/\\ Conversationタイトル /\\", "UTF-8")
            val timeInMillis = System.currentTimeMillis()
            TopScreen(
                onNavigateToConversation = {
                    navController.navigate("${Nav.ConversationScreen.name}/$title/$timeInMillis")
                },
                onNavigateToMapFragment = {
                    navController.navigate(Nav.GoogleMapsScreen.name)
                },
                onNavigateToButtonSample = {
                    navController.navigate(Nav.ButtonSampleScreen.name)
                },
                onNavigateToTextSampleScreen = {
                    navController.navigate(Nav.TextSampleScreen.name)
                },
                onNavigateToFlowTestScreen = {
                    navController.navigate(Nav.FlowTestScreen.name)
                }
            )
        }
    }

    private fun NavGraphBuilder.conversationScreen(navController: NavHostController) {
        composable(
            route = "${Nav.ConversationScreen.name}/{title}/{time}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("time") { type = NavType.LongType },
            ),
            enterTransition = NavAnim.enterTransition,
            exitTransition = NavAnim.exitTransition,
            popEnterTransition = NavAnim.popEnterTransition,
            popExitTransition = NavAnim.popExitTransition,
        ) { backStackEntry ->
            val title = URLDecoder.decode(backStackEntry.arguments?.getString("title") ?: "", "UTF-8")
            val timeInMillis = backStackEntry.arguments?.getLong("time")
            ConversationScreen(title, timeInMillis)
        }
    }

    private fun NavGraphBuilder.googleMapsScreen(navController: NavHostController) {
        composable(
            route = Nav.GoogleMapsScreen.name,
        ) {
            GoogleMapsScreen()
        }
    }

    private fun NavGraphBuilder.buttonSampleScreen(navController: NavHostController) {
        composable(
            route = Nav.ButtonSampleScreen.name,
        ) {
            ButtonSampleScreen()
        }
    }

    private fun NavGraphBuilder.textSampleScreen(navController: NavHostController) {
        composable(
            route = Nav.TextSampleScreen.name,
        ) {
            TextSampleScreen()
        }
    }

    private fun NavGraphBuilder.flowTestScreen(navController: NavHostController) {
        composable(
            route = Nav.FlowTestScreen.name,
        ) {
            FlowTestScreen()
        }
    }
}
