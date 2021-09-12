package com.simbirsoft.bonus.presentation.view.bonuses.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simbirsoft.bonus.databinding.BonusItemBinding
import com.simbirsoft.bonus.presentation.model.bonuses.BonusItem

class BonusItemAdapter(
    private val items: MutableList<BonusItem> = mutableListOf(),
    private val onItemPressed: (BonusItem, View) -> Unit,
) : RecyclerView.Adapter<BonusItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonusItemViewHolder {
        val binding = BonusItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BonusItemViewHolder(binding, onItemPressed)
    }

    override fun onBindViewHolder(holder: BonusItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun swapData(newItems: List<BonusItem>) {
        this.items.clear()
        this.items.addAll(newItems)
    }

    fun getItems(): List<BonusItem> = items
}

class BonusItemViewHolder(
    private val binding: BonusItemBinding,
    private val onItemPressed: (BonusItem, View) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BonusItem) {
        with(binding) {
            itemTitleTextView.text = item.title
            itemDescriptionTextView.text = item.description
            itemIcon.setImageDrawable(item.icon)
            root.transitionName = item.title.toString() + item.description.toString()
            root.setOnClickListener {
                onItemPressed(item, root)
            }
        }
    }
}