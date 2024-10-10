package com.example.enfecapplication.di.module

import com.app.enfecapplication.di.BaseUrl
import com.example.enfecapplication.data.api.NetworkService
import com.example.enfecapplication.data.repository.MainScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return "https://jsonplaceholder.typicode.com/"
    }

    @Singleton
    @Provides
    fun provideNetworkService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideGsonConvertedFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = com.google.gson.GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//    ): OkHttpClient {
//        val okHttpClient = OkHttpClient().newBuilder()
//        return okHttpClient.build()
//    }

    @Singleton
    @Provides
    fun provideMainScreenRepository(networkUtils: NetworkService): MainScreenRepository {
        return MainScreenRepository(networkUtils)
    }

//    @Provides
//    @Singleton
//    fun provideSessionManager(@ApplicationContext context: Context) = SessionManger(context)

//    @Singleton
//    @Provides
//    fun provideLocalDatabase(
//        @DatabaseName databaseName: String,
//        @ApplicationContext context: Context
//    ): MainAppDatabase {
//        return Room.databaseBuilder(
//            context, MainAppDatabase::class.java, databaseName
//        ).build()
//    }




}