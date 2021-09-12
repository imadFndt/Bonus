package com.simbirsoft.bonus.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.interactor.profile.ProfileInteractor
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.presentation.model.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileMapper: Mapper<User, Profile>,
    profileInteractor: ProfileInteractor
) : ViewModel() {

    val profile
        get() = profileFlow.asStateFlow()
            .filterNotNull()

    private val profileFlow = MutableStateFlow<Profile?>(null)

    init {
        viewModelScope.launch {

            profileInteractor.loadProfile()
                .also { profileFlow.emit(profileMapper.map(it)) }
        }
    }
}