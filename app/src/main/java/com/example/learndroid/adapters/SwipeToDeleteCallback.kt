package com.example.learndroid.adapters

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.provider.CalendarContract
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.learndroid.R
import com.example.learndroid.data.entity.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class SwipeToDeleteCallback(
    private val adapter: WordsListAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private val background = ColorDrawable(Color.MAGENTA)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        when (direction) {
            ItemTouchHelper.LEFT -> adapter.removeWord(position)
            ItemTouchHelper.RIGHT -> {
                val text = viewHolder.itemView.textView.text.toString()
                adapter.removeWord(position)
                adapter.insertWord(Word(text))
            }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        val deleteIcon : Drawable
        val top = itemView.top + itemView.paddingTop
        val bottom = itemView.bottom - 21

        if (dX < 0) {
            background.color = ContextCompat.getColor(recyclerView.context, R.color.colorRed)
            deleteIcon = ContextCompat.getDrawable(recyclerView.context, R.drawable.ic_baseline_delete_34)!!
            background.setBounds(itemView.right + dX.toInt(),
                top, itemView.right,
                bottom)
            background.draw(c)
        } else {
            background.color = ContextCompat.getColor(recyclerView.context, R.color.colorIndigo)
            deleteIcon =
                ContextCompat.getDrawable(recyclerView.context, R.drawable.ic_baseline_refresh_24)!!
            background.setBounds(itemView.left + dX.toInt(),
                top, itemView.left,
                bottom)
            background.draw(c)
        }
        val intrinsicWidth = deleteIcon.intrinsicWidth
        val intrinsicHeight = deleteIcon.intrinsicHeight
        val deleteIconTop = top + 12
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
        val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
        val deleteIconRight = deleteIconLeft + intrinsicWidth
        val deleteIconBottom = itemView.bottom - intrinsicHeight + 20

        deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteIcon.draw(c)
    }
}