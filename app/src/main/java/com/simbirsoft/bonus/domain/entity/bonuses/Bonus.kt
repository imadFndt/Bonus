package com.simbirsoft.bonus.domain.entity.bonuses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bonus(
    val type: BonusType,
    val title: String,
    val description: String,
    val additionalInfo: List<String>,
): Parcelable
