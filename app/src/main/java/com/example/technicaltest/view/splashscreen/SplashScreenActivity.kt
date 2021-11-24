package com.example.technicaltest.view.splashscreen

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.technicaltest.core.base.BaseActivity
import com.example.technicaltest.core.ext.disposedBy
import com.example.technicaltest.databinding.ActivitySplashBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenActivity :  BaseActivity<ActivitySplashBinding>(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    private val DEFAULT_DELAY: Long = 2_000
    private val router = SplashRouter()

    override fun enableBackButton(): Boolean = false

    override fun bindToolbar(): Toolbar? = null

    override fun getUiBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        Observable.just("DELAY")
            .delay(DEFAULT_DELAY, TimeUnit.MILLISECONDS)
            .subscribe {
                router.openMainScreen(this)
            }.disposedBy(disposable)
    }

    override fun initUiListener() {}


    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
