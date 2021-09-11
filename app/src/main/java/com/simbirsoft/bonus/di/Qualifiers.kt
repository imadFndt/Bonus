package com.simbirsoft.bonus.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MockRepository

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RealRepository