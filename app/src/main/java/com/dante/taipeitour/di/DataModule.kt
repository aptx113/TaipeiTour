package com.dante.taipeitour.di

import com.dante.taipeitour.data.repository.DefaultTaipeiTourRepository
import com.dante.taipeitour.data.repository.TaipeiTourRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsTaipeiTourRepository(
        taipeiTourRepository: DefaultTaipeiTourRepository,
    ): TaipeiTourRepository
}
