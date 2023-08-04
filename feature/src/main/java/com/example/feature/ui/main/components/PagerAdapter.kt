package com.example.feature.ui.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daniil.core.domain.entity.Weather
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun SetPager(state:PagerState, weather: Weather?) {
    VerticalPager(
        modifier = Modifier
            .defaultMinSize(minHeight = 150.dp)
            .alpha(0.8f)
            .padding(top = 20.dp)
            .heightIn(max = 150.dp)
            .fillMaxWidth(0.95f)
            .background(
                color = Color.Transparent, shape = RoundedCornerShape(6.dp)
            ),
        count = 2, state = state,
        horizontalAlignment = Alignment.Start
    ) { page ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
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
                repeat(state.pageCount) { iteration ->
                    val color =
                        if (state.currentPage == iteration) Color.DarkGray else Color.LightGray
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
}