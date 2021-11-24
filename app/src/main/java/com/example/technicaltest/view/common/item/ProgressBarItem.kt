package com.example.technicaltest.view.common.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.technicaltest.databinding.ItemProgressbarBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ProgressBarItem : AbstractBindingItem<ItemProgressbarBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemProgressbarBinding {
        return ItemProgressbarBinding.inflate(inflater, parent, false)
    }
}