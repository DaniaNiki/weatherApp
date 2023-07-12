package com.example.feature.ui.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.viewinterop.AndroidView
import com.example.feature.BuildConfig
import com.example.feature.theme.Typography
import com.example.feature.theme.createBackgroundGradient
import com.example.feature.theme.dayGradient
import com.example.feature.theme.main
import com.example.feature.theme.mainScreenBackground
import com.example.feature.theme.mainScreenGradient
import com.example.feature.theme.morningGradient
import com.example.feature.theme.morningWeather
import com.example.feature.theme.nightGradient
import com.example.feature.ui.main.components.Moon
import com.example.feature.ui.main.components.WeatherFirstPage
import com.example.feature.ui.main.viewmodel.WeatherViewModel
import com.example.feature.util.getLocalTime
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Float.min
import java.lang.Math.cos
import java.lang.Math.sin
import java.time.LocalDateTime
import java.util.*

@Composable
fun SetMainScreen(viewModel: WeatherViewModel) {
    MapKitFactory.setApiKey("2f6e832b-711e-42d7-ae58-90e368e2a01a")
    MapKitFactory.initialize(LocalContext.current)
    MapKitFactory.getInstance().onStart()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .createBackgroundGradient(mainScreenGradient)
    ) {
        Info(viewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Info(viewModel: WeatherViewModel) {
    val weather by viewModel.weather.observeAsState()
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    var cityName by remember {
        mutableStateOf("Ярославль")
    }

    LaunchedEffect(Unit) {
        this.launch {
            viewModel.getWeatherByCity("Ярославль")
        }
    }

    val hours = weather?.let { it.location.localTime.getLocalTime(timeZone = TimeZone.getTimeZone(it.location.timeZoneId)) }
            ?: LocalDateTime.now().hour
    val brushByTime by remember {
        mutableStateOf(
            when (hours) {
                in 6..11 -> morningGradient // с 6 до 12 часов утра - белый цвет
                in 12..17 -> dayGradient // с 12 до 18 часов дня - серый цвет
                in 18..24 -> nightGradient // с 18 до 24 часов вечера - светло-серый цвет // todo: заменить на вечерний градиент
                else -> nightGradient// ночью - черный цвет
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .createBackgroundGradient(mainScreenGradient),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top= 25.dp))
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
        }))
        VerticalPager(
            modifier = Modifier
                .defaultMinSize(minHeight = 150.dp)
                .alpha(0.8f)
                .padding(top = 20.dp)
                .shadow(12.dp, ambientColor = Color.Black)
                .heightIn(max = 150.dp)
                .fillMaxWidth(0.95f)
                .background(
                    brush = brushByTime, shape = RoundedCornerShape(6.dp)
                ), count = 2, state = pagerState,
            horizontalAlignment = Alignment.Start
        ) { page ->
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row {
                    when (page) {
                        0 -> {
                            weather?.let { WeatherFirstPage(weather = it) }
                        }
                    }
                }
                Column(
                    modifier = Modifier.height(50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            GlideImage(imageModel ="https:" +weather?.currentWeather?.condition?.icon, alignment = Alignment.Center, modifier = Modifier.size(120.dp))
            Spacer(modifier = Modifier.padding(top = 10.dp))
            weather?.currentWeather?.temperature?.let { Text(text = it.toString(), style = Typography.body1) }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(text = weather?.currentWeather?.condition?.text ?: "123545", style = Typography.body1)
        }
        if (weather != null) {
            val placeMark = ImageProvider.fromResource(
                LocalContext.current,
                com.example.feature.R.drawable.icon_map_point
            )
            SetCityMap(
                onPointChange = { map ->
                    val point = Point(weather?.location?.latitude ?: 0.0, weather?.location?.longitude ?: 0.0)
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