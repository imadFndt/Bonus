package com.simbirsoft.bonus.data.state

import com.simbirsoft.bonus.data.entity.UserToken

interface Preferences {
    val token: UserToken?

    suspend fun setToken(token: UserToken?)
}