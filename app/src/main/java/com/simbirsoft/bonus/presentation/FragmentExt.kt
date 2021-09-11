package com.simbirsoft.bonus.presentation

import androidx.fragment.app.Fragment
import com.simbirsoft.bonus.NavigationListener

val Fragment.navigationListener
    get() = activity as? NavigationListener