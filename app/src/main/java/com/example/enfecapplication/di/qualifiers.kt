package com.app.enfecapplication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedPreferencesName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccessToken