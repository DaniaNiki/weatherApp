package com.example.feature.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.feature.theme.dayGradient
import com.example.feature.theme.morningGradient
import com.example.feature.theme.nightGradient
import com.example.feature.ui.main.components.Moon
import com.example.feature.ui.main.components.WeatherFirstPage
import com.example.feature.ui.main.viewmodel.WeatherViewModel
import com.example.feature.util.getLocalTime
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

@Composable
fun SetMainScreen(viewModel: WeatherViewModel) {
    MapKitFactory.setApiKey("2f6e832b-711e-42d7-ae58-90e368e2a01a")
    MapKitFactory.initialize(LocalContext.current)
    MapKitFactory.getInstance().onStart()
    Surface(modifier = Modifier.fillMaxSize()) {
        Info(viewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Info(viewModel: WeatherViewModel) {
    val weather by viewModel.weather.observeAsState()
    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(Unit) {
        this.launch {
            viewModel.getWeatherByCity("Jaroslavl")
        }
    }

    val hours = weather?.let { it.location.localTime.getLocalTime(timeZone = TimeZone.getTimeZone(it.location.timeZoneId)) } ?: LocalDateTime.now().hour
    val brushByTime by remember {
        mutableStateOf(
            when {
                hours >= 10 -> dayGradient
                hours in 19 downTo 6 -> nightGradient
                hours in 6 .. 10 -> morningGradient
                else -> { dayGradient }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(1f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalPager(
            modifier = Modifier
                .defaultMinSize(minHeight = 150.dp)
                .padding(top = 20.dp)
                .shadow(10.dp, ambientColor = DefaultShadowColor)
                .heightIn(max = 150.dp)
                .fillMaxWidth(0.95f)
                .background(
                    brush = brushByTime, shape = RoundedCornerShape(6.dp)
                ), count = 2, state = pagerState, horizontalAlignment = Alignment.CenterHorizontally
        ) { page ->
            when (page) {
                0 -> {
                    weather?.let { WeatherFirstPage(weather = it) }
                }
            }
        }
        if (weather != null) {
            val placeMark = ImageProvider.fromResource(
                LocalContext.current,
                com.example.feature.R.drawable.icon_place_marker_48
            )
            SetCityMap(
                onPointChange = { map ->
                    val point = Point(
                        weather?.location?.latitude ?: 0.0,
                        weather?.location?.longitude ?: 0.0
                    )
                    map.map.apply {
                        move(CameraPosition(point, 10.5f, 40.0f, 10.0f))
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
            .fillMaxWidth()
            .height(250.dp),
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