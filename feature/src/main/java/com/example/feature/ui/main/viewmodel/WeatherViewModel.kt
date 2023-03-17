package com.example.feature.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.daniil.core.data.remote.source.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) :ViewModel() {

}