package com.simbirsoft.bonus.presentation

import androidx.fragment.app.Fragment
import com.simbirsoft.bonus.MainActivity

val Fragment.mainActivity
    get() = activity as? MainActivity