package com.example.technicaltest.view.main.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.domain.model.ResultData
import com.example.technicaltest.core.ext.loadImage
import com.example.technicaltest.databinding.ItemMovieBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieItem(val discoverMovie: ResultData) : AbstractBindingItem<ItemMovieBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemMovieBinding {
       return ItemMovieBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemMovieBinding, payloads: List<Any>) {
        binding.run {
            this.itemImageMovie.loadImage(discoverMovie.posterPath.toString())
            this.itemTextMovie.text = discoverMovie.title.toString()
            this.itemMovieTextAdult.text = if(discoverMovie.adult == true)  "R18+" else "R13+"
            this.itemMovieTextLanguage.text = discoverMovie.originalLanguage?.toUpperCase()
        }
    }
}