package com.simbirsoft.bonus.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.presentation.view.profile.list.ProfileItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ProfileViewModel : ViewModel() {
    val profile
        get() = profileFlow.asStateFlow()
            .filterNotNull()

    private val profileFlow = MutableStateFlow<Profile?>(null)

    init {
        profileFlow.value = Profile(
            name = "Иван иванов",
            avatarUrl = "",
            status = listOf("Ментор", "Собеседующий", "Еще что-то"),
            department = "Mobile Android",
            about = "TODO",
            achievements = listOf(
                ProfileItem("", 0),
                ProfileItem("", 0),
                ProfileItem("", 0),
                ProfileItem("", 0),
                ProfileItem("", 0),
                ProfileItem("", 0),
                ProfileItem("", 0),
            )
        )
    }
}

data class Profile(
    val name: String,
    val avatarUrl: String? = null,
    val status: List<String>,
    val department: String,
    val about: String,
    val achievements: List<ProfileItem>,
)