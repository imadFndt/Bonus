package com.simbirsoft.bonus.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.simbirsoft.bonus.databinding.FragmentProfileBinding
import com.simbirsoft.bonus.presentation.view.profile.list.ProfileAdapter
import com.simbirsoft.bonus.presentation.viewmodel.profile.Profile
import com.simbirsoft.bonus.presentation.viewmodel.profile.ProfileViewModel
import kotlinx.coroutines.flow.collect

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileAdapter
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProfileAdapter {}
        binding.profileAchievementsList.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.profile.collect { profile ->
                binding.setProfile(profile)
            }
        }
    }

    private fun FragmentProfileBinding.setProfile(profile: Profile) {
        adapter.submitList(profile.achievements)

        profileName.text = profile.name
        profileAboutContent.text = profile.about
        profileDepartment.text = profile.department
        profileChips.setStatus(profile.status)
    }

    private fun ChipGroup.setStatus(statuses: List<String>) {
        removeAllViews()
        statuses.forEach { status ->
            addView(
                Chip(context).apply {
                    text = status
                }
            )
        }
    }
}