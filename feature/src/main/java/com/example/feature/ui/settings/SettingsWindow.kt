package com.example.feature.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature.theme.createBackgroundGradient
import com.example.feature.theme.mainScreenGradient
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Preview
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsView() {
//    val permissionState = rememberMultiplePermissionsState(
//        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    var checked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .createBackgroundGradient(mainScreenGradient)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Включить определение геолокации")
            Switch(
                checked = checked/*permissionState.allPermissionsGranted*/,
                onCheckedChange = { value ->
                    checked = value
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Green,
                    uncheckedBorderColor = Color.Black,
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.White
                )
            )
        }
    }
}