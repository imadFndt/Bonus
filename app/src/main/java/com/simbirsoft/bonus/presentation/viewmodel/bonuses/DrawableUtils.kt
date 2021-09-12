package com.simbirsoft.bonus.presentation.viewmodel.bonuses

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

fun layerDrawable(vararg ds: Drawable): LayerDrawable = LayerDrawable(ds)