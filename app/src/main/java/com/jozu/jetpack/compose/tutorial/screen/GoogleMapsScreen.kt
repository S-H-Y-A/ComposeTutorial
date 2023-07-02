package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 *
 * Created by jozuko on 2023/07/02.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
/** 東京駅 */
val initPos = LatLng(35.6809591, 139.7673068)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleMapsScreen() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initPos, 10f)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { contentPadding ->
            GoogleMap(
                modifier = Modifier.padding(contentPadding),
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = MarkerState(position = initPos),
                    title = "東京駅です"
                )
            }
        },
    )
}