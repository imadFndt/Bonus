package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    @MockRepository private val repository: MainRepository
) : ProfileInteractor {

    override suspend fun loadProfile(): User {
        return repository.getCurrentUser()
    }
}