package com.example.technicaltest.core.di

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppGlideModule : com.bumptech.glide.module.AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val option = RequestOptions().apply {
            format(DecodeFormat.PREFER_ARGB_8888)
            diskCacheStrategy(DiskCacheStrategy.ALL)
        }
        builder.run {
            setLogLevel(Log.ERROR)
            setMemoryCache(LruResourceCache((1024 * 1024 * 20).toLong())) //20mb
            setDiskCache(InternalCacheDiskCacheFactory(context))
            setDefaultRequestOptions(option)
        }
    }
}