package com.simbirsoft.bonus

import androidx.fragment.app.Fragment

interface NavigationListener {

    fun onBackPressed()

    fun addFragment(fragment: Fragment)

    fun replaceFragment(fragment: Fragment)

    fun setBottomNavigationBarVisibility(isVisible: Boolean)

    fun showLoader()

    fun hideLoader()
}