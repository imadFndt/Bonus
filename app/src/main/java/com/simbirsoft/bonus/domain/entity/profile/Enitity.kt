package com.simbirsoft.bonus.domain.entity.profile


data class Profile(
    val name: String,
    val avatarUrl: String? = null,
    val status: List<String>,
    val department: String,
    val about: String,
    val achievements: List<Achievement>,
)

data class Achievement(val type: String, val count: Int)