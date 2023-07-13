package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * ボタンのサンプル集
 *
 * Created by jozuko on 2023/07/13.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@Composable
fun ButtonSampleScreen() {
    var buttonEnabledState by remember { mutableStateOf(ToggleableState(true)) }
    val buttonEnable = buttonEnabledState == ToggleableState.On

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Button Enable?")
                Switch(
                    checked = buttonEnable,
                    onCheckedChange = {
                        buttonEnabledState = when (buttonEnabledState) {
                            ToggleableState.On -> ToggleableState.Off
                            ToggleableState.Off -> ToggleableState.On
                            ToggleableState.Indeterminate -> ToggleableState.On
                        }
                    },
                )
            }
            ModifierDeco(buttonEnable)
            BackgroundColor(buttonEnable)
            CustomColor(buttonEnable)
            CustomShape(buttonEnable)
            WordWrapButton(buttonEnable)
        }
    }
}

@Preview
@Composable
fun ButtonSampleScreenPreview() {
    ButtonSampleScreen()
}

@Composable
private fun ModifierDeco(buttonEnable: Boolean) {
    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = { /*TODO*/ },
    ) {
        Text("No Modifier")
    }
    Button(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Red),
        enabled = buttonEnable,
        onClick = { /*TODO*/ },
    ) {
        Text("Modifier.background")
    }
    Button(
        modifier = Modifier
            .padding(4.dp)
            .border(border = BorderStroke(10.dp, Color.Blue)),
        enabled = buttonEnable,
        onClick = { /*TODO*/ },
    ) {
        Text("Modifier.border")
    }
}

@Composable
private fun BackgroundColor(buttonEnable: Boolean) {
    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        colors = ButtonDefaults.buttonColors(),
    ) {
        Text("ButtonDefaults.buttonColors")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,
        ),
        colors = ButtonDefaults.elevatedButtonColors(),
    ) {
        Text("ButtonDefaults.elevatedButtonColors")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        colors = ButtonDefaults.filledTonalButtonColors(),
    ) {
        Text("ButtonDefaults.filledTonalButtonColors")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.outlinedButtonColors(),
    ) {
        Text("ButtonDefaults.outlinedButtonColors")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        colors = ButtonDefaults.textButtonColors(),
    ) {
        Text("ButtonDefaults.textButtonColors")
    }
}

@Composable
private fun CustomColor(buttonEnable: Boolean) {
    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.Green,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.LightGray,
        ),
    ) {
        Text("ButtonDefaults.buttonColors")
    }
}

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
@Composable
private fun CustomShape(buttonEnable: Boolean) {
    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = RoundedCornerShape(percent = 50),
    ) {
        Text("RoundedCornerShape(percent = 50)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = RoundedCornerShape(percent = 10),
    ) {
        Text("RoundedCornerShape(percent = 10)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = RoundedCornerShape(size = 10.dp),
    ) {
        Text("RoundedCornerShape(size = 10.dp)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = RoundedCornerShape(
            topStart = 5.dp,
            topEnd = 10.dp,
            bottomStart = 15.dp,
            bottomEnd = 20.dp,
        ),
    ) {
        Text("RoundedCornerShape(eachCorners)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = CutCornerShape(percent = 10),
    ) {
        Text("CutCornerShape(percent = 10)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = CutCornerShape(size = 4.dp),
    ) {
        Text("CutCornerShape(size = 4.dp)")
    }

    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
        shape = GenericShape { size, layoutDirection ->
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
        },
    ) {
        Text("GenericShape")
    }
}

@Composable
private fun WordWrapButton(buttonEnable: Boolean) {
    Button(
        modifier = Modifier.padding(4.dp),
        enabled = buttonEnable,
        onClick = {},
    ) {
        Text("WordWrap\nButton")
    }
}