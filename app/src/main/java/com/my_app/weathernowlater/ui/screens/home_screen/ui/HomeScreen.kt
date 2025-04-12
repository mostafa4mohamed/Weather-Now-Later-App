package com.my_app.weathernowlater.ui.screens.home_screen.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.my_app.weathernowlater.state.NetworkState
import com.my_app.weathernowlater.ui.screens.home_screen.vm.ForecastIntent
import com.my_app.weathernowlater.ui.screens.home_screen.vm.ForecastState
import com.my_app.weathernowlater.ui.screens.home_screen.vm.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val forecastState by viewModel.forecastState.collectAsStateWithLifecycle()
    val currentWeatherState by viewModel.getCurrentWeatherStateFlow.collectAsState()

    val onButtonClick: () -> Unit = {
        if (viewModel.inputText.value.isNotBlank()) {
            val city = viewModel.inputText.value
            viewModel.sendIntent(ForecastIntent.LoadForecast(city))
            viewModel.getCurrentWeather()
        }
    }

    when {
        forecastState is ForecastState.Loading || currentWeatherState is NetworkState.Loading -> {
            LoadingContent(viewModel.inputText, onButtonClick)
        }

        forecastState is ForecastState.Error || currentWeatherState is NetworkState.Error -> {
            val errorMsg = (forecastState as? ForecastState.Error)?.message
                ?: (currentWeatherState as? NetworkState.Error)?.msg
            ErrorContent(message = errorMsg, inputText = viewModel.inputText, onRetryClick = onButtonClick)
        }

        forecastState is ForecastState.Success || currentWeatherState is NetworkState.Result -> {
            HomeContent(viewModel, onButtonClick)
        }

        else -> {
            InitContent(viewModel.inputText, onButtonClick)
        }
    }
}
