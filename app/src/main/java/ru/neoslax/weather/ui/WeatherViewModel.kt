package ru.neoslax.weather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.neoslax.weather.domain.entities.WeatherState
import ru.neoslax.weather.domain.usecases.GetCurrentLocationUseCase
import ru.neoslax.weather.domain.usecases.GetWeatherDataUseCase
import ru.neoslax.weather.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            getCurrentLocationUseCase()?.let { location ->
                when (val result = getWeatherDataUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            error = null,
                            weatherInfo = result.data
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message,
                            weatherInfo = null

                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    weatherInfo = null,
                    error = "No location permissions granted"
                )

            }
        }
    }
}