package com.markosopcic.core.ui

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.markosopcic.core.R

fun RecyclerView.addDividerWithoutLast(divider: Drawable) {

    if (layoutManager !is LinearLayoutManager)
        return

    addItemDecoration(object :
            DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation) {

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val numChildren = parent.adapter!!.itemCount

            for (i in 0 until numChildren - 1) {
                val child = parent.getChildAt(i)
                if (child != null) {
                    val params = child.layoutParams as RecyclerView.LayoutParams
                    val top = child.bottom + params.bottomMargin
                    val bottom = top + divider.intrinsicHeight
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(c)
                }
            }
        }
    })
}

fun RecyclerView.addBasicDivider() {
    val inset = resources.getDimension(R.dimen.common_margin).toInt()
    val divider = InsetDrawable(ResourcesCompat.getDrawable(resources, R.drawable.line_separator, null), inset, 0, inset, 0)
    addDividerWithoutLast(divider)
}
