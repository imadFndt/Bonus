package com.simbirsoft.bonus.di.activity

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ProvidesModule {

    @Provides
    fun fragmentManager(activity: FragmentActivity) = activity.supportFragmentManager
}