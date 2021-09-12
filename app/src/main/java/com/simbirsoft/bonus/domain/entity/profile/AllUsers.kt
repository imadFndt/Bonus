package com.simbirsoft.bonus.domain.entity.profile

import com.google.gson.annotations.SerializedName

data class AllUsers(
    @SerializedName("users")
    val users: List<User>
)