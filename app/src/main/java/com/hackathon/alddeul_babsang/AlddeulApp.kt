package com.hackathon.alddeul_babsang

import android.app.Application
import com.hackathon.alddeul_babsang.BuildConfig.NAVER_CLIENT_ID
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlddeulApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initNaverMapSdk()
        setTimber()
    }

    private fun initNaverMapSdk() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(NAVER_CLIENT_ID)
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }
}