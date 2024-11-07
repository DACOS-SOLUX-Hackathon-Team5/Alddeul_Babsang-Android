package com.hackathon.alddeul_babsang.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AlddeulRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccessToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserPreferences
