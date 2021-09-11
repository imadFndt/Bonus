package com.simbirsoft.bonus.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.*
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BottomNavigationRouter @Inject constructor(
    private val fragmentManager: FragmentManager,
) {

    companion object {
        const val CURRENT_TAG_KEY = "CurrentTag"
    }

    private var currentTag: String? = null
    private var tags: Set<String> = emptySet()

    fun init(
        tagFragmentMap: Map<String, () -> Fragment>,
        @IdRes containerId: Int,
    ) {
        tags = tagFragmentMap.keys
        addFragments(tagFragmentMap, containerId)
    }

    fun saveStateToBundle(state: Bundle) {
        state.putString(CURRENT_TAG_KEY, currentTag)
    }

    fun restoreState(bundle: Bundle) {
        currentTag = bundle.getString(CURRENT_TAG_KEY)
    }

    fun chooseFragment(tag: String) {
        if (tag == currentTag) return
        fragmentManager.commit{
            hideNotNull(fragmentManager.findFragmentByTag(currentTag))

            val selectedFragment = fragmentManager.findFragmentByTag(tag)
                ?: throw IllegalStateException("Fragment with tag $tag not found")

            show(selectedFragment)
            currentTag = tag
        }
    }

    private fun addFragments(tagFragmentMap: Map<String, () -> Fragment>, containerId: Int) {
        fragmentManager.commitNow {

            tagFragmentMap.entries.forEach { (tag, fragmentBuilder) ->
                fragmentManager.findFragmentByTag(tag)?.let { return@forEach }

                val fragment = fragmentBuilder()
                add(containerId, fragment, tag)
                hide(fragment)
            }
        }
    }

    private fun FragmentTransaction.hideNotNull(fragment: Fragment?) {
        fragment ?: return
        hide(fragment)
    }
}