package com.simbirsoft.bonus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.transition.Fade
import com.google.android.material.transition.MaterialContainerTransform
import com.simbirsoft.bonus.databinding.ActivityMainBinding
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.presentation.view.bonuses.BonusesFragment
import com.simbirsoft.bonus.presentation.view.custom.ButtonView
import com.simbirsoft.bonus.presentation.view.custom.LoaderDialog
import com.simbirsoft.bonus.presentation.view.profile.ProfileFragment
import com.simbirsoft.bonus.presentation.view.timeline.TimeLineFragment
import com.simbirsoft.bonus.util.BottomNavigationRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationListener {

    @Inject
    lateinit var bottomNavigationRouter: BottomNavigationRouter

    private lateinit var binding: ActivityMainBinding
    private val loadingDialog by lazy { LoaderDialog() }
    private val bottomNavigationListener: (MenuItem) -> Boolean = { item ->
        when (item.itemId) {
            R.id.bonuses_item -> bottomNavigationRouter.chooseFragment(BonusesFragment.TAG)
            R.id.profile_item -> bottomNavigationRouter.chooseFragment(ProfileFragment.TAG)
            R.id.timeline_item -> bottomNavigationRouter.chooseFragment(TimeLineFragment.TAG)
            else -> Unit
        }

        true
    }

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
                    TimeLineFragment.TAG to TimeLineFragment::newInstance
                ),
                binding.fragmentContainer.id
            )
            bottomNavigationRouter.chooseFragment(TimeLineFragment.TAG)
        }

        binding.bottomNavigationView.setOnItemSelectedListener(bottomNavigationListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bottomNavigationRouter.saveStateToBundle(outState)
    }

    override fun showLoader() {
        if (loadingDialog.isAdded.not()) {
            loadingDialog.show(supportFragmentManager, LoaderDialog.TAG)
        }
    }

    override fun hideLoader() {
        if (loadingDialog.isAdded) {
            loadingDialog.dismiss()
        }
    }


    override fun setBottomNavigationBarVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == MINIMUM_FRAGMENTS) {
            finish()
        }

        super.onBackPressed()
    }

    override fun chooseBonus(type: BonusType) {
        binding.bottomNavigationView.setOnItemSelectedListener(null)
        bottomNavigationRouter.chooseBonus(BonusesFragment.TAG, type)
        binding.bottomNavigationView.selectedItemId = R.id.bonuses_item
        binding.bottomNavigationView.setOnItemSelectedListener(bottomNavigationListener)
    }

    override fun replaceFragmentWithAnimation(view: View, fragment: Fragment) {
        supportFragmentManager.commit {
            fragment.apply {
                sharedElementEnterTransition = MaterialContainerTransform()
                sharedElementReturnTransition = MaterialContainerTransform()
                enterTransition = Fade()
                exitTransition = Fade()
            }
            addSharedElement(view, "shared_element_container")
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun setBackgroundTimeLineColor(color: Int){
        binding.root.setBackgroundColor(R.color.colorPrimary)
    }

    override fun showPopUpWindow() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_new_bonus, null)

        val popupWindow = PopupWindow(
            popupView,
            resources.getDimensionPixelSize(R.dimen.margin_350dp),
            resources.getDimensionPixelSize(R.dimen.margin_450dp),
            true
        )

        popupWindow.showAtLocation(findViewById(R.id.rootView), Gravity.CENTER, 0, 0)
        popupView.findViewById<ButtonView>(R.id.done)?.apply {
            isEnabled = true
            setOnClickListener {
                popupWindow.dismiss()
                chooseBonus(BonusType.BONUS)
            }
        }
    }

    companion object {
        private const val MINIMUM_FRAGMENTS = 0

        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}