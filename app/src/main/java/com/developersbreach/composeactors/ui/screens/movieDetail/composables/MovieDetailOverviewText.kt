package com.developersbreach.composeactors.ui.screens.movieDetail.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailOverviewText(
    overview: String?
) {
    var expanded by remember { mutableStateOf(false) }

    Text(
        text = overview.toString(),
        maxLines = if (expanded) Int.MAX_VALUE else 4,
        overflow = if (expanded) TextOverflow.Visible else TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { expanded = !expanded },
        style = TextStyle(
            lineHeight = 20.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Justify,
            fontSize = 16.sp
        )
    )
}