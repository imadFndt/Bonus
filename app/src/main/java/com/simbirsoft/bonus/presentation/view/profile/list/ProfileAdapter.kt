package com.simbirsoft.bonus.presentation.view.profile.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simbirsoft.bonus.databinding.ItemProfileAchievementsBinding

class ProfileAdapter : ListAdapter<ProfileItem, ProfileAdapter.ProfileViewHolder>(ProfileItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileAchievementsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProfileViewHolder(

        binding: ItemProfileAchievementsBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProfileItem) = Unit
    }

    class ProfileItemDiffCallback : DiffUtil.ItemCallback<ProfileItem>() {

        override fun areItemsTheSame(oldItem: ProfileItem, newItem: ProfileItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: ProfileItem, newItem: ProfileItem) = oldItem == newItem
    }
}

data class ProfileItem(val type: String, val count: Int)