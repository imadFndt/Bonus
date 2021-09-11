package com.simbirsoft.bonus.domain.entity.bonuses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BonusType : Parcelable{
    ACTIVITY,
    MERCH,
    BONUS,
}