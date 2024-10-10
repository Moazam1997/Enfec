package com.example.enfecapplication.data.api


import com.example.enfecapplication.mainScreen.model.PostData
import com.example.enfecapplication.mainScreen.model.UsersData
import retrofit2.http.GET

interface NetworkService {

    @GET("users")
    suspend fun getUsers(): List<UsersData>


    @GET("posts")
    suspend fun getPosts(): List<PostData>
}