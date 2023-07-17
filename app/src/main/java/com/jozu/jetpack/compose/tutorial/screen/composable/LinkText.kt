package com.jozu.jetpack.compose.tutorial.screen.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

/**
 *
 * Created by jozuko on 2023/07/17.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun LinkText(
    text: String,
    linkTextList: List<LinkTextData>,
    modifier: Modifier = Modifier,
    onClick: (url: String) -> Unit,
) {
    val linkSpanStyle = SpanStyle(
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
    )

    val annotatedText = buildAnnotatedString {
        append(text)
        linkTextList.forEach { linkData ->
            val start = text.indexOf(linkData.label)
            if (start >= 0) {
                val end = start + linkData.label.length
                addUrlAnnotation(UrlAnnotation(linkData.url), start, end)
                addStyle(linkSpanStyle, start, end)
            }
        }
    }

    ClickableText(
        text = annotatedText,
        modifier = modifier,
        onClick = { pos ->
            annotatedText.getUrlAnnotations(start = pos, end = pos).firstOrNull()?.let { range ->
                onClick.invoke(range.item.url)
            }
        },
    )
}

data class LinkTextData(val label: String, val url: String)
