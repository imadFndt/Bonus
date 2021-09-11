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
import com.simbirsoft.bonus.presentation.view.profile.ProfileFragment
import com.simbirsoft.bonus.util.BottomNavigationRouter
import dagger.assisted.AssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var bottomNavigationRouter: BottomNavigationRouter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        savedInstanceState?.let {
            bottomNavigationRouter.restoreState(it)
        } ?: run {
            bottomNavigationRouter.init(
                mapOf(
                    BonusesFragment.TAG to BonusesFragment::newInstance,
                    ProfileFragment.TAG to ProfileFragment::newInstance,
                ),
                binding.fragmentContainer.id
            )
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bonuses_item -> bottomNavigationRouter.chooseFragment(BonusesFragment.TAG)
                R.id.profile_item -> bottomNavigationRouter.chooseFragment(ProfileFragment.TAG)
                else -> Unit
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        bottomNavigationRouter.saveStateToBundle(outState)
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
}