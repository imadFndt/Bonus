package com.simbirsoft.bonus.presentation.view.bonuses.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.Item

class BonusItemAdapter(
    private val items: MutableList<Item> = mutableListOf()
): RecyclerView.Adapter<BonusItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonusItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bonus_item, parent, false)
        return BonusItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BonusItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun swapData(newItems: List<Item>) {
        this.items.clear()
        this.items.addAll(newItems)
    }

    fun getItems(): List<Item> = items
}

class BonusItemViewHolder(
    private val view: View,
): RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        view.findViewById<TextView>(R.id.textView).text = item.text
    }
}