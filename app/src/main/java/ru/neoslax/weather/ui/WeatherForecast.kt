package ru.neoslax.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neoslax.weather.domain.entities.WeatherData
import ru.neoslax.weather.domain.entities.WeatherState
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Today", fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.size(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    WeatherForecastEntity(
                        data = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}

@Composable
fun WeatherForecastEntity(
    modifier: Modifier = Modifier,
    data: WeatherData,
    textColor: Color = Color.White
) {

    val formattedTime = remember(data) {
        data.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = formattedTime, color = textColor)
        Spacer(modifier = Modifier.size(6.dp))
        Image(
            painter = painterResource(id = data.weatherType.iconRes),
            contentDescription = data.weatherType.weatherDesc,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(text = "${data.temperature} C", color = textColor, fontWeight = FontWeight.Bold)
    }
}