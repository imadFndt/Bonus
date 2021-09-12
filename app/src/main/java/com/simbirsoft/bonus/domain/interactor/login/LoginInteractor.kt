package com.simbirsoft.bonus.domain.interactor.login

import com.simbirsoft.bonus.domain.entity.profile.User

interface LoginInteractor {

    suspend fun login(login: String, password: String)

    suspend fun findSavedUser(): User?
}