package com.example.enfecapplication.data.repository

import android.service.autofill.UserData
import com.example.enfecapplication.data.api.NetworkService
import com.example.enfecapplication.mainScreen.model.PostData
import com.example.enfecapplication.mainScreen.model.UsersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainScreenRepository@Inject constructor(private val networkService: NetworkService) {

    fun geUserData(): Flow<List<UsersData>> {
        return flow {
            emit(networkService.getUsers())
        }
    }

    fun getPostData(): Flow<List<PostData>> {
        return flow {
            emit(networkService.getPosts())
        }
    }
}