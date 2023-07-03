package com.jozu.jetpack.compose.tutorial.screen

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            ) {
                Column {
                    Map(
                        modifier = Modifier.weight(1f),
                        cameraPositionState = cameraPositionState,
                        markerItems = markerItems,
                        onMapLoaded = {
                            isMapLoaded = true
                        }
                    )
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
    onMapLoaded: () -> Unit = {},
) {
    var polylineItems by remember { mutableStateOf<List<LatLng>>(listOf()) }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded,
        onMapClick = { latLng ->
            polylineItems = polylineItems + latLng
        },
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