package com.example.technicaltest.view.main

import android.app.Activity
import com.example.technicaltest.view.detail.DetailActivity

class MainRouter {

    fun gotoDetailActivity(source : Activity,idMovie : String){
        source.startActivity(DetailActivity.createIntent(source,idMovie))
    }
}