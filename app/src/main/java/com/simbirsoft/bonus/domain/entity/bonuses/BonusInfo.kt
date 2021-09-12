package com.simbirsoft.bonus.domain.entity.bonuses

data class BonusInfo(
    val canGetBonus: Boolean,
    val errorText: String = "",
    val emailText: String = "",
)
