package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.domain.entity.profile.User

interface ProfileInteractor {
    suspend fun loadProfile(): User?
}