package com.example.composerediexpress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LargeButton(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(46.dp)
        .background(Color(0xFF0560FA), RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF)
        )
    }
}