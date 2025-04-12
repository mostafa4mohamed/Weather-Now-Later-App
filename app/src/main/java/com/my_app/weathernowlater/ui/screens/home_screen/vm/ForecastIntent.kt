package com.my_app.weathernowlater.ui.screens.home_screen.vm

sealed class ForecastIntent {
    data class LoadForecast(val city: String) : ForecastIntent()
    object Idle : ForecastIntent()
}