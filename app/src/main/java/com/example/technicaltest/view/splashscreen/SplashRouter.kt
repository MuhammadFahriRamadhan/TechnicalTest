package com.example.technicaltest.view.splashscreen

import com.example.technicaltest.view.main.MainActivity

class SplashRouter {
    fun openMainScreen(source: SplashScreenActivity) {
        source.startActivity(MainActivity.createIntent(source))
        source.finish()
    }
}