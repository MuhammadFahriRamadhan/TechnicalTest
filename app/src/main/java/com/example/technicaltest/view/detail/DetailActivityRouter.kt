package com.example.technicaltest.view.detail

import android.content.Context
import android.content.Intent
import android.net.Uri

class DetailActivityRouter {
    fun openYoutube(source : Context, key : String?){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+key))
        source.startActivity(intent)
    }
}