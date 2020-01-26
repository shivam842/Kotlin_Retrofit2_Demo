package com.lgdev.kotlindemo.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class CustomItemDecoration(private val spaceHeight: Int, private val orientation: Int) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        try {
            if (orientation == RecyclerView.HORIZONTAL) {
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = spaceHeight
                }
                if (parent.getChildLayoutPosition(view) == parent.adapter!!.itemCount - 1) {
                    outRect.right = spaceHeight
                }
            }
            if (orientation == RecyclerView.VERTICAL) {
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = spaceHeight
                }
                if (parent.getChildLayoutPosition(view) == parent.adapter!!.itemCount - 1) {
                    outRect.bottom = spaceHeight
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}