package com.example.feature.ui.settings

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature.BuildConfig
import com.example.feature.R
import com.example.feature.theme.Typography
import com.example.feature.theme.createBackgroundGradient
import com.example.feature.theme.mainScreenGradient
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsView(navController:NavController) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    var checked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .createBackgroundGradient(mainScreenGradient)
    ) {
        Row(modifier = Modifier.padding(start = 10.dp, top = 25.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_left_38), contentDescription = null, tint = Color.Black)
            }
            Text(text = "Настройки приложения", textAlign = TextAlign.Center, style = Typography.body1, color = Color.Black)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Включить определение геолокации", modifier = Modifier.padding(start = 15.dp), style = Typography.body2, color = Color.Black)
            Switch(
                checked = permissionState.allPermissionsGranted,
                onCheckedChange = { value ->
                    if(value){
                        permissionState.launchMultiplePermissionRequest()
                    }
                },
                thumbContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.location_marker_38),
                        contentDescription = null,
                        tint = if (checked) Color.Red else Color.Black
                    )
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Transparent,
                    checkedTrackColor = Color.Green,
                    uncheckedBorderColor = Color.Black,
                    uncheckedThumbColor = Color.Transparent,
                    uncheckedTrackColor = Color.White
                )
            )
        }
        Column(modifier = Modifier
            .weight(0.5f)
            .fillMaxWidth()
            .padding(bottom = 100.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Версия приложения: 0.1 (01)", style = Typography.body1, color = Color.Black)
        }
    }
}