package com.simbirsoft.bonus.domain.interactor.login

interface LoginInteractor {
    suspend fun login(login: String, password: String)
}