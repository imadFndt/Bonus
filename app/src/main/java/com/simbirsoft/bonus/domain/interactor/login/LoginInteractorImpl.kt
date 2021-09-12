package com.simbirsoft.bonus.domain.interactor.login

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(

    @MockRepository private val repository: MainRepository

) : LoginInteractor {

    override suspend fun login(login: String, password: String) {
        repository.login(login, password)
    }

    override suspend fun findSavedUser(): User? {
        return repository.getCurrentUser()
    }
}