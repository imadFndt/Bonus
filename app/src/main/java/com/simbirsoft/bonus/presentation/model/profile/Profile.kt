package com.simbirsoft.bonus.presentation.model.profile

data class Profile(
    val name: String,
    val roles: List<String>,
    val department: String,
    val city: String,
    val joinDate: String,
    val size: Int,
    val achievements: List<Achievement>,
)

data class Achievement(val type: String, val count: Int)
