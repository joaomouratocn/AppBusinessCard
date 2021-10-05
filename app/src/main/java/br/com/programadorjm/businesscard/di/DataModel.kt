package br.com.programadorjm.businesscard.di

import android.app.Application
import br.com.programadorjm.businesscard.data.CardRepository
import br.com.programadorjm.businesscard.data.CardRepositoryImpl
import br.com.programadorjm.businesscard.domain.usecases.UseCases
import br.com.programadorjm.businesscard.domain.usecases.UseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModel {
    @Singleton
    @Binds
    abstract fun binUseCases(
        useCases: UseCasesImpl
    ):UseCases

    @Singleton
    @Binds
    abstract fun bindCardRepository(
        cardRepository: CardRepositoryImpl
    ):CardRepository
}