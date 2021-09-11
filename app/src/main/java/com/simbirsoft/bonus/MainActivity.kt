package com.simbirsoft.bonus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.transition.Fade
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.transition.MaterialContainerTransform
import com.simbirsoft.bonus.databinding.ActivityMainBinding
import com.simbirsoft.bonus.presentation.view.bonuses.BonusesFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            bottomNavigationRouter.restoreState(it)
        } ?: run {
            bottomNavigationRouter.init(
                mapOf(
                    BonusesFragment.TAG to { BonusesFragment.newInstance() },
                    ProfileFragment.TAG to ProfileFragment::newInstance,
                    TimeLineFragment.TAG to TimeLineFragment::newInstance
                ),
                binding.fragmentContainer.id
            )
            bottomNavigationRouter.chooseFragment(TimeLineFragment.TAG)
        }

        setBottomNavigationBackground()
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bonuses_item -> bottomNavigationRouter.chooseFragment(BonusesFragment.TAG)
                R.id.profile_item -> bottomNavigationRouter.chooseFragment(ProfileFragment.TAG)
                R.id.timeline_item -> bottomNavigationRouter.chooseFragment(TimeLineFragment.TAG)
                else -> Unit
            }

            return@setOnItemSelectedListener true
        }
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

    override fun replaceFragment(view: View, fragment: Fragment) {
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

    private fun setBottomNavigationBackground() {
        val color = ContextCompat.getColorStateList(this, R.color.colorPrimary)

        val radius = resources.getDimension(R.dimen.margin_12dp)
        val shapeDrawable : MaterialShapeDrawable = binding.bottomNavigationView.background as MaterialShapeDrawable
        shapeDrawable.shapeAppearanceModel = shapeDrawable.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
        shapeDrawable.fillColor = color
    }

    companion object {
        private const val MINIMUM_FRAGMENTS = 0

        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}