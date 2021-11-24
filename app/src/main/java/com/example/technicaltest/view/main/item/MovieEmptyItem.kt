package com.example.technicaltest.view.main.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.technicaltest.R
import com.example.technicaltest.databinding.ItemEmptyMovieBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieEmptyItem(val message: Int = R.string.str_movie_notfound) :
    AbstractBindingItem<ItemEmptyMovieBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmptyMovieBinding {
        return ItemEmptyMovieBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemEmptyMovieBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.emptyMovieText.text = binding.root.context.getString(message)
    }
}