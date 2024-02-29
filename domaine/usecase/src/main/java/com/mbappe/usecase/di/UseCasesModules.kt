package com.mbappe.usecase.di

import com.mbappe.usecase.PaginatedShowsUseCase
import com.mbappe.usecase.interactor.PaginatedShowsInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModules {

    @Binds
    internal abstract fun bindsGetPaginatedShowsUseCase(
        getPaginatedShowsInteractor: PaginatedShowsInteractor,
    ): PaginatedShowsUseCase

}