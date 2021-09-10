package com.simbirsoft.bonus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.simbirsoft.bonus.databinding.ActivityMainBinding
import com.simbirsoft.bonus.presentation.view.bonuses.BonusesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bonuses_item -> callNavigationFragmentByTag(BonusesFragment.TAG)
                else -> Unit
            }

            return@setOnItemSelectedListener true
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
                else -> null
            }
        }

        desiredFragment?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, fragment, tag)
                commit()
            }
        }
    }

}