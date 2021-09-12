package com.simbirsoft.bonus.di

import com.simbirsoft.bonus.data.repo.MainRepositoryImpl
import com.simbirsoft.bonus.data.repo.MockRepositoryImpl
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractor
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractorImpl
import com.simbirsoft.bonus.domain.interactor.login.LoginInteractor
import com.simbirsoft.bonus.domain.interactor.login.LoginInteractorImpl
import com.simbirsoft.bonus.domain.interactor.profile.ProfileInteractor
import com.simbirsoft.bonus.domain.interactor.profile.ProfileInteractorImpl
import com.simbirsoft.bonus.domain.interactor.timeline.TimelineInteractor
import com.simbirsoft.bonus.domain.interactor.timeline.TimelineInteractorImpl
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.domain.repo.MainRepository
import com.simbirsoft.bonus.presentation.mapper.bonuses.BonusItemMapper
import com.simbirsoft.bonus.presentation.model.bonuses.BonusItem

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    @RealRepository
    abstract fun mainRepository(
        repo: MainRepositoryImpl
    ): MainRepository

    @Binds
    @Singleton
    @MockRepository
    abstract fun mockRepository(
        repo: MockRepositoryImpl
    ): MainRepository

    @Binds
    @Singleton
    abstract fun bonusesInteractor(
        repo: BonusesInteractorImpl
    ): BonusesInteractor

    @Binds
    @Singleton
    abstract fun loginInteractor(
        repo: LoginInteractorImpl
    ): LoginInteractor

    @Binds
    @Singleton
    abstract fun profileInteractor(
        repo: ProfileInteractorImpl
    ): ProfileInteractor

    @Binds
    @Singleton
    abstract fun timelineInteractor(
        repo: TimelineInteractorImpl
    ): TimelineInteractor

    @Binds
    @Singleton
    abstract fun bonusMapper(
        mapper: BonusItemMapper
    ): Mapper<Bonus, BonusItem>
}