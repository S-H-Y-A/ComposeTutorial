package com.jozu.jetpack.compose.tutorial.screen

import android.graphics.Point
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
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
    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initPos, 10f)
    }
    val markerItems = remember { mutableStateListOf<LatLng>() }
    var polylineItems by remember { mutableStateOf<List<LatLng>>(listOf()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ) {
                        Map(
                            modifier = Modifier.matchParentSize(),
                            cameraPositionState = cameraPositionState,
                            markerItems = markerItems,
                            polylineItems = polylineItems,
                            onMapLoaded = {
                                isMapLoaded = true
                            }
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = { offset ->
                                            if (!cameraPositionState.isMoving) {
                                                val pressedLatLng = cameraPositionState.projection?.fromScreenLocation(Point(offset.x.toInt(), offset.y.toInt()))
                                                if (pressedLatLng != null) {
                                                    polylineItems = polylineItems + pressedLatLng
                                                }
                                            }
                                        },
                                    )
                                },
                        )
                    }
                    Buttons(
                        modifier = Modifier.fillMaxWidth(),
                        isMapLoaded = isMapLoaded,
                        cameraPositionState = cameraPositionState,
                        markerItems = markerItems,
                    )
                }
            }
        },
    )
}

@Composable
fun Map(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    markerItems: List<LatLng>,
    polylineItems: List<LatLng>,
    onMapLoaded: () -> Unit = {},
) {
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded,
    ) {
        markerItems.forEach { markerItem ->
            Marker(
                state = MarkerState(position = markerItem),
                title = "${markerItem.latitude},${markerItem.longitude}"
            )
        }
        if (polylineItems.count() > 1) {
            Polyline(
                points = polylineItems,
            )
        }
    }
}

@Composable
fun Buttons(
    modifier: Modifier,
    isMapLoaded: Boolean,
    cameraPositionState: CameraPositionState,
    markerItems: MutableList<LatLng>,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            enabled = isMapLoaded,
            onClick = {
                if (!cameraPositionState.isMoving) {
                    val latLngBounds = cameraPositionState.projection?.visibleRegion?.latLngBounds
                    if (latLngBounds != null) {
                        val northEast = latLngBounds.northeast
                        val southWest = latLngBounds.southwest
                        val latitude = northEast.latitude - ((northEast.latitude - southWest.latitude) / 10.0) * (markerItems.count() + 1)
                        val longitude = northEast.longitude - ((northEast.longitude - southWest.longitude) / 10.0) * (markerItems.count() + 1)
                        markerItems.add(LatLng(latitude, longitude))
                    }
                }
            },
        ) {
            Text("Marker追加")
        }
    }
}