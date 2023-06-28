package com.jozu.jetpack.compose.tutorial.screen.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * アイコン持ちのボタン
 *
 * Created by jozuko on 2023/06/28.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@Composable
fun IconButton(
    label: String,
    imageVector: ImageVector? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
    ) {
        if (imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }
        Text(label)
    }
}
