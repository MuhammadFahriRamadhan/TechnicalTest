package com.example.technicaltest.view.detail.item

import android.view.View
import com.example.technicaltest.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISubItem
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewItem(private val content : String) : AbstractExpandableItem<ReviewItem.ViewHolderFaqItem>(),
    ISubItem<ReviewItem.ViewHolderFaqItem> {

    override val type: Int
        get() = hashCode()

    override val layoutRes: Int
        get() = R.layout.item_review

    override fun getViewHolder(v: View): ViewHolderFaqItem = ViewHolderFaqItem(v)

    inner class ViewHolderFaqItem(val view: View) : FastAdapter.ViewHolder<ReviewItem>(view) {
        override fun bindView(item: ReviewItem, payloads: List<Any>) {
            view.itemTextContent.text = item.content
        }

        override fun unbindView(item: ReviewItem) {
            view.itemTextContent.text = ""
        }
    }
}