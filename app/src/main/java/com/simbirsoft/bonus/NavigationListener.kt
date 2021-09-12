package com.simbirsoft.bonus

import android.view.View
import androidx.fragment.app.Fragment

interface NavigationListener {

    fun onBackPressed()

    fun replaceFragment(view: View, fragment: Fragment)

    fun setBottomNavigationBarVisibility(isVisible: Boolean)

    fun showLoader()

    fun hideLoader()

    fun showPopUpWindow()
}