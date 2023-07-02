package com.jozu.jetpack.compose.tutorial.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

/**
 *
 * Created by jozuko on 2023/07/02.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
object NavAnim {
    val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth / 2 },
                animationSpec = tween(),
            )
        }

    val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
        get() = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth / -2 },
                animationSpec = tween(),
            )
        }

    val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth / -2 },
                animationSpec = tween(),
            )
        }

    val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
        get() = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(),
            )
        }
}