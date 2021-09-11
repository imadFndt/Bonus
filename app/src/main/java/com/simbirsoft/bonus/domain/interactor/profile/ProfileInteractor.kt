package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.domain.entity.profile.Profile

interface ProfileInteractor {
    suspend fun loadProfile(): Profile
}