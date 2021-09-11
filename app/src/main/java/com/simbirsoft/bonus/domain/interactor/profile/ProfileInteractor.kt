package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.presentation.viewmodel.profile.Profile

interface ProfileInteractor {
    fun loadProfile(): Profile
}