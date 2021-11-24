package com.example.technicaltest.view.detail.item

import android.view.View
import androidx.core.view.ViewCompat
import com.example.technicaltest.R
import com.mikepenz.fastadapter.ClickListener
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import kotlinx.android.synthetic.main.item_expandable_review.view.*

class ReviewExanpandable(val text : String) : AbstractExpandableItem<ReviewExanpandable.ViewHolderExpandableFaq>(),
    IClickable<ReviewExanpandable> {

    private var clickListener: ClickListener<ReviewExanpandable>? = null

    override val layoutRes: Int
        get() = R.layout.item_expandable_review


    override val type: Int
        get() = hashCode()

    override fun getViewHolder(v: View): ViewHolderExpandableFaq  = ViewHolderExpandableFaq(v)

    inner class ViewHolderExpandableFaq(val view : View) : FastAdapter.ViewHolder<ReviewExanpandable>(view){
        override fun bindView(item: ReviewExanpandable, payloads: List<Any>) {
            view.run {
                clearAnimation()
                itemExpandapleTitle.text = item.text
                if (item.subItems.isEmpty()) {
                    itemExpandleIcon.visibility = View.GONE
                } else {
                    itemExpandleIcon.visibility = View.VISIBLE
                }

                if (item.isExpanded) {
                    itemExpandleIcon.rotation = 180f
                } else {
                    itemExpandleIcon.rotation = 0f
                }
            }
        }

        override fun unbindView(item: ReviewExanpandable) {
            view.itemExpandapleTitle.text = null
            view.itemExpandleIcon.clearAnimation()
        }

    }

    override var onItemClickListener: ClickListener<ReviewExanpandable>? =  { v: View?, adapter: IAdapter<ReviewExanpandable>, item: ReviewExanpandable, position: Int ->
        if (item.subItems.isNotEmpty()) {
            v?.itemExpandleIcon?.let {
                if (!item.isExpanded) {
                    ViewCompat.animate(it).rotation(0f).start()
                } else {
                    ViewCompat.animate(it).rotation(180f).start()
                }
            }
        }
        clickListener?.invoke(v, adapter, item, position) ?: true
    }

        set(value) {
            clickListener = value
            field = value
        }

    override var isSelectable: Boolean
        get() = subItems.isEmpty()
        set(value) {
            super.isSelectable = value
        }


    override var onPreItemClickListener: ClickListener<ReviewExanpandable>?
        get() = null
        set(value) {}
}