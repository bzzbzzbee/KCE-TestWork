package com.bzzbzzbee.kcetestwork.utils

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bzzbzzbee.kcetestwork.presentation.cars.adapter.SwipeToActionCallback

object RecyclerUtils {
    fun attachSwipeToAction(
        context: Context,
        recyclerView: RecyclerView,
        onSwipe: (Int) -> Unit
    ) {
        val swipeToActionCallback: SwipeToActionCallback =
            object : SwipeToActionCallback(context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                    val position = viewHolder.adapterPosition
                    onSwipe.invoke(position)
                }
            }

        val helper = ItemTouchHelper(swipeToActionCallback)
        helper.attachToRecyclerView(recyclerView)
    }
}