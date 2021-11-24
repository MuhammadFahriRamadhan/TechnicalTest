package com.example.technicaltest.view.detail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.technicaltest.R
import com.example.technicaltest.databinding.ItemEmptyMovieBinding
import com.example.technicaltest.databinding.ItemEmptyReviewBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ReviewEmpty(val message: Int = R.string.str_movie_notfound) :
    AbstractBindingItem<ItemEmptyReviewBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmptyReviewBinding {
        return ItemEmptyReviewBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemEmptyReviewBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.emptyReviewText.text = binding.root.context.getString(message)
    }
}