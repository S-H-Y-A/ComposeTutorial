package com.jozu.jetpack.compose.tutorial.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.jozu.jetpack.compose.tutorial.screen.composable.AutoSizeText
import com.jozu.jetpack.compose.tutorial.screen.composable.LinkText
import com.jozu.jetpack.compose.tutorial.screen.composable.LinkTextData

/**
 *
 * Created by jozuko on 2023/07/17.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextSampleScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                LinkTextSample()
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                NormalText()
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextLine(maxLines = 1)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextLine(maxLines = 2)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextIgnoreFontScale(maxLines = 2)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextLine(maxLines = 10)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextStep(maxLines = 2)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextPreset(maxLines = 2)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .fillMaxWidth(),
            ) {
                AutoSizeTextLetterSpacing(maxLines = 7)
            }
        }
    }
}

@Preview
@Composable
fun TextSampleScreenPreview() {
    TextSampleScreen()
}

@Composable
private fun LinkTextSample() {
    val uriHandler = LocalUriHandler.current

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

@Composable
private fun NormalText() {
    Text(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        fontSize = TextUnit(16.0f, TextUnitType.Sp),
    )
}

@Composable
private fun AutoSizeTextLine(maxLines: Int) {
    AutoSizeText(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        maxLines = maxLines,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        autoSizeMaxTextSize = 16.0f,
        autoSizeMinTextSize = 8.0f,
    )
}

@Composable
private fun AutoSizeTextStep(maxLines: Int) {
    AutoSizeText(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        maxLines = maxLines,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        autoSizeMaxTextSize = 16.0f,
        autoSizeMinTextSize = 8.0f,
        autoSizeStepGranularity = 2.0f,
    )
}

@Composable
private fun AutoSizeTextPreset(maxLines: Int) {
    AutoSizeText(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        maxLines = maxLines,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        autoSizePresetSizes = listOf(
            8.0f,
            10.0f,
            16.0f,
        )
    )
}

@Composable
private fun AutoSizeTextLetterSpacing(maxLines: Int) {
    AutoSizeText(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        maxLines = maxLines,
        letterSpacing = TextUnit(10.0f, TextUnitType.Sp),
        lineHeight = TextUnit(10.0f, TextUnitType.Sp),
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        autoSizeMaxTextSize = 16.0f,
        autoSizeMinTextSize = 1.0f,
    )
}

@Composable
private fun AutoSizeTextIgnoreFontScale(maxLines: Int) {
    AutoSizeText(
        text = """吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。この書生というのは時々我々を捕えて煮て食うという話である。""",
        maxLines = maxLines,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        autoSizeMaxTextSize = 16.0f,
        autoSizeMinTextSize = 8.0f,
        ignoreFontScale = true,
    )
}
