package com.simbirsoft.bonus.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.domain.entity.profile.Profile
import com.simbirsoft.bonus.domain.interactor.profile.ProfileInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
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