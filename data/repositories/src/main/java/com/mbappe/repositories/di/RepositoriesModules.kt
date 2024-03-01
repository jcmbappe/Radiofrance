package com.mbappe.repositories.di

import com.mbappe.repositories.StationsRepository
import com.mbappe.repositories.ShowsRepository
import com.mbappe.repositories.implementation.StationsRepositoryImpl
import com.mbappe.repositories.implementation.ShowsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModules {

    @Binds
    internal abstract fun bindsBrandsRepository(
        brandsRepositoryImpl: StationsRepositoryImpl,
    ): StationsRepository


    @Binds
    internal abstract fun bindsShowsRepository(
        showsRepositoryImpl: ShowsRepositoryImpl,
    ): ShowsRepository

}