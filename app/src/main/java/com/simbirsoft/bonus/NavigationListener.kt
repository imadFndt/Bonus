package com.simbirsoft.bonus

import android.view.View
import androidx.fragment.app.Fragment
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType

interface NavigationListener {

    fun onBackPressed()

    fun chooseBonus(type: BonusType)

    fun replaceFragmentWithAnimation(view: View, fragment: Fragment)

    fun setBottomNavigationBarVisibility(isVisible: Boolean)

    fun showLoader()

    fun hideLoader()

    fun showPopUpWindow()
}