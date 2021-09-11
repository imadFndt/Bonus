package com.simbirsoft.bonus.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.domain.interactor.profile.ProfileInteractor
import com.simbirsoft.bonus.presentation.view.profile.list.ProfileItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    profileInteractor: ProfileInteractor
) : ViewModel() {

    val profile
        get() = profileFlow.asStateFlow()
            .filterNotNull()

    private val profileFlow = MutableStateFlow<Profile?>(null)

    init {
        profileInteractor.loadProfile()
            .also { profileFlow.value = it }
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