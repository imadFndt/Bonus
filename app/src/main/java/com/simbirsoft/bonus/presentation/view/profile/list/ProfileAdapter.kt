package com.simbirsoft.bonus.presentation.view.profile.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.ItemProfileAchievementsBinding
import com.simbirsoft.bonus.domain.entity.profile.Achievement

class ProfileAdapter : ListAdapter<Achievement, ProfileAdapter.ProfileViewHolder>(ProfileItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileAchievementsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProfileViewHolder(
        private val binding: ItemProfileAchievementsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Achievement) {
            binding.apply {
                profileItemTitle.text = item.type
                profileItemCount.text = item.count.toString()

                //TODO
                profileItemImage.setImageResource(R.drawable.ic_mic)
            }
        }
    }

    class ProfileItemDiffCallback : DiffUtil.ItemCallback<Achievement>() {

        override fun areItemsTheSame(oldItem: Achievement, newItem: Achievement) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Achievement, newItem: Achievement) = oldItem == newItem
    }
}