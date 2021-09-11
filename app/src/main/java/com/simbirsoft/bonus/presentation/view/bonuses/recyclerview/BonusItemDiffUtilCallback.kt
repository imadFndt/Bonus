package com.simbirsoft.bonus.presentation.view.bonuses.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.Item

class BonusItemDiffUtilCallback(
    private val oldItems: List<Item>,
    private val newItems: List<Item>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldItems[oldItemPosition].text == newItems[newItemPosition].text

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldItems[oldItemPosition] == newItems[newItemPosition]
}