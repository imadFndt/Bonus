package com.simbirsoft.bonus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.simbirsoft.bonus.databinding.ActivityMainBinding
import com.simbirsoft.bonus.presentation.view.bonuses.BonusesFragment
import com.simbirsoft.bonus.presentation.view.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bonuses_item -> callNavigationFragmentByTag(BonusesFragment.TAG)
                R.id.profile_item -> callNavigationFragmentByTag(ProfileFragment.TAG)
                else -> Unit
            }

            return@setOnItemSelectedListener true
        }
    }

    fun setBottomNavigationBarVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }

    fun popBackStack() {
        supportFragmentManager.popBackStack(null, 0)
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    private fun callNavigationFragmentByTag(tag: String) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        var desiredFragment = supportFragmentManager.findFragmentByTag(tag)

        if (desiredFragment != null) {
            return
        } else {
            desiredFragment = when (tag) {
                BonusesFragment.TAG -> BonusesFragment.newInstance()
                ProfileFragment.TAG -> ProfileFragment.newInstance()
                else -> null
            }
        }

        desiredFragment?.let { fragment ->
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, fragment, tag)
            }
        }
    }

}