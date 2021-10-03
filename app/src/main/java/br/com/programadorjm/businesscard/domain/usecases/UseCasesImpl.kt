package br.com.programadorjm.businesscard.domain.usecases

import br.com.programadorjm.businesscard.data.CardRepositoryImpl
import br.com.programadorjm.businesscard.domain.model.CardModel

class UseCasesImpl(private val repositoryImpl: CardRepositoryImpl):UseCases {
    override suspend fun getAllCards(): List<CardModel> {
        return repositoryImpl.getAllCards()
    }

    override suspend fun insertCard(cardModel: CardModel) {
        repositoryImpl.insertCard(cardModel)
    }

    override suspend fun updateCard(cardModel: CardModel) {
        repositoryImpl.updatesCard(cardModel)
    }

    override suspend fun deleteCard(cardModel: CardModel) {
        repositoryImpl.deleteCard(cardModel)
    }
}