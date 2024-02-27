package com.mbappe.repositories.di

import com.mbappe.repositories.BrandsRepository
import com.mbappe.repositories.ShowsRepository
import com.mbappe.repositories.implementation.BrandsRepositoryImpl
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
        brandsRepositoryImpl: BrandsRepositoryImpl,
    ): BrandsRepository


    @Binds
    internal abstract fun bindsShowsRepository(
        showsRepositoryImpl: ShowsRepositoryImpl,
    ): ShowsRepository

}