package com.example.feature.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SetMainScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Info()
    }
}

@Preview
@Composable
fun Info() {
  Column(modifier = Modifier.fillMaxWidth(0.8f), horizontalAlignment = Alignment.CenterHorizontally) {
      Row(horizontalArrangement = Arrangement.Center) {
          Text("тут будет иконка glide")
      }
      Row(horizontalArrangement = Arrangement.Center) {
          Text("тут будет город")
      }
      Row(horizontalArrangement = Arrangement.Center) {
          Text("тут будет дата")
      }
  }
}