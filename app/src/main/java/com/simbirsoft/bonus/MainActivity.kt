package com.simbirsoft.bonus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.simbirsoft.bonus.databinding.ActivityMainBinding
import com.simbirsoft.bonus.presentation.view.bonuses.BonusesFragment
import com.simbirsoft.bonus.presentation.view.custom.LoaderDialog
import com.simbirsoft.bonus.presentation.view.profile.ProfileFragment
import com.simbirsoft.bonus.presentation.view.timeline.TimeLineFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var binding: ActivityMainBinding
    private val loadingDialog by lazy { LoaderDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bonuses_item -> callNavigationFragmentByTag(BonusesFragment.TAG)
                R.id.profile_item -> callNavigationFragmentByTag(ProfileFragment.TAG)
                R.id.timeline_item -> callNavigationFragmentByTag(TimeLineFragment.TAG)
                else -> Unit
            }

            return@setOnItemSelectedListener true
        }

        callNavigationFragmentByTag(TimeLineFragment.TAG)
    }

    fun showLoader() {
        if (loadingDialog.isAdded.not()) {
            loadingDialog.show(supportFragmentManager, LoaderDialog.TAG)
        }
    }

    fun hideLoader() {
        if (loadingDialog.isAdded) {
            loadingDialog.dismiss()
        }
    }

    fun setBottomNavigationBarVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }

    override fun onBackPressed() {
        if(supportFragmentManager.fragments.size == 1){
            finish()
        }

        super.onBackPressed()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            add(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    private fun callNavigationFragmentByTag(tag: String) {
        supportFragmentManager.findFragmentByTag(tag)?.let {
            return
        } ?: run {
            when (tag) {
                BonusesFragment.TAG -> replaceFragment(BonusesFragment.newInstance())
                ProfileFragment.TAG -> replaceFragment(ProfileFragment.newInstance())
                TimeLineFragment.TAG -> replaceFragment(TimeLineFragment.newInstance())
                else -> null
            }
        }
    }
}