package com.example.enfecapplication.mainScreen.viewmodel


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.enfecapplication.data.repository.MainScreenRepository
import com.example.enfecapplication.mainScreen.model.PostData
import com.example.enfecapplication.mainScreen.model.UsersData
import com.example.enfecapplication.ui.base.BaseViewModel
import com.example.enfecapplication.ui.base.UiState
import com.example.enfecapplication.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val mainScreenRepository: MainScreenRepository,
                                             private val networkUtils: NetworkUtils
) : BaseViewModel() {

    private val _uiStateUserData =
        MutableSharedFlow<UiState<List<UsersData>>>()

    val uiStateUserData: SharedFlow<UiState<List<UsersData>>> =
        _uiStateUserData


    private val _uiStatePostData =
        MutableSharedFlow<UiState<List<PostData>>>()

    val uiStatePostData: SharedFlow<UiState<List<PostData>>> =
        _uiStatePostData



    fun getUserData() {
        if (networkUtils.isNetworkAvailable()) {
        viewModelScope.launch {

            mainScreenRepository.geUserData().catch {
                Log.e("DATAAPI", "Error collecting user: ${it}")
            }.collect {
                _uiStateUserData.emit(UiState.Success(it))
            }
        }
        }
        else
        {
            Log.e("DATAAPI", "Error collecting user: ")
        }
    }

    fun getPostData(userID : Int) {
        if (networkUtils.isNetworkAvailable()) {
            viewModelScope.launch {

                mainScreenRepository.getPostData().catch {
                    Log.e("DATAAPI", "Error collecting post: ${it}")
                }.collect {

                        _uiStatePostData.emit(UiState.Success(it))


                }
            }
        }
        else
        {
            Log.e("DATAAPI", "Error collecting post: ")
        }
    }




}