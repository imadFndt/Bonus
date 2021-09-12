package com.simbirsoft.bonus.presentation.model.bonuses

import android.graphics.drawable.Drawable

data class BonusItem(
    val icon: Drawable,
    val title: CharSequence,
    val description: CharSequence,
    val additionalInfo: List<String>?,
)