package com.example.feature.ui.main

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.feature.theme.Typography
import com.example.feature.ui.main.components.SetPager
import com.example.feature.ui.main.viewmodel.WeatherViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.skydoves.landscapist.glide.GlideImage
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@Composable
fun SetMainScreen(viewModel: WeatherViewModel) {
        Info(viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Info(viewModel: WeatherViewModel) {
    val context = LocalContext.current
    val weather by viewModel.weather.observeAsState()
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    var cityName by rememberSaveable {
        mutableStateOf("Ярославль")
    }

    LaunchedEffect(Unit) {
        this.launch {
            viewModel.getWeatherByCity(cityName)
            getCurrentLocation(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 25.dp))
        TextField(value = cityName, onValueChange = { newValue ->
            cityName = newValue
        },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(6.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                coroutineScope.launch {
                    viewModel.getWeatherByCity(cityName)
                }
                focusManager.clearFocus()
            })
        )
        SetPager(state = pagerState, weather = weather)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = weather?.location?.city ?: "Город", style = Typography.h1)
            GlideImage(
                imageModel = "https:" + weather?.currentWeather?.condition?.icon,
                alignment = Alignment.Center,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            weather?.currentWeather?.temperature?.let {
                Text(
                    text = it.toString(),
                    style = Typography.body1
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = weather?.currentWeather?.condition?.text ?: "Температура",
                style = Typography.body1
            )
        }
        if (weather != null) {
            val placeMark = ImageProvider.fromResource(
                LocalContext.current,
                com.example.feature.R.drawable.icon_map_point
            )
            SetCityMap(
                onPointChange = { map ->
                    val point = Point(
                        weather?.location?.latitude ?: 0.0,
                        weather?.location?.longitude ?: 0.0
                    )
                    map.map.apply {
                        move(CameraPosition(point, 12f, 40.0f, 10.0f))
                        //ставит метку на карте по координатам
                        map.map.mapObjects.addPlacemark(point, placeMark)
                    }
                }
            )
        }
    }
}

@Composable
fun SetCityMap(onPointChange: (MapView) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.96f)
            .clip(RoundedCornerShape(6.dp))
            .height(300.dp)
            .padding(bottom = 75.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DisposableEffect(
            AndroidView(factory = { MapView(it) }, update = { map ->
                onPointChange(map)
            })
        ) {
            onDispose {
                MapKitFactory.getInstance().onStop()
            }
        }
    }
}

@Suppress("MissingPermission")
fun getCurrentLocation(context: Context){
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        Timber.e(location.toString())
    }
}