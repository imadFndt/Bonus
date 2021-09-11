package com.simbirsoft.bonus.presentation.view.bonuses.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus

class BonusItemDiffUtilCallback(
    private val oldItems: List<Bonus>,
    private val newItems: List<Bonus>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldItems[oldItemPosition].title == newItems[newItemPosition].title

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldItems[oldItemPosition] == newItems[newItemPosition]
}