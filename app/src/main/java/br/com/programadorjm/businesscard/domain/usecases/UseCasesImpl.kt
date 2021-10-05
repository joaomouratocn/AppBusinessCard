package br.com.programadorjm.businesscard.domain.usecases

import br.com.programadorjm.businesscard.data.CardRepository
import br.com.programadorjm.businesscard.data.CardRepositoryImpl
import br.com.programadorjm.businesscard.domain.model.CardModel
import javax.inject.Inject

class UseCasesImpl @Inject constructor(private val cardRepository: CardRepository):UseCases {
    override suspend fun getAllCards(): List<CardModel> {
        return cardRepository.getAllCards()
    }

    override suspend fun insertCard(cardModel: CardModel) {
        cardRepository.insertCard(cardModel)
    }

    override suspend fun updateCard(cardModel: CardModel) {
        cardRepository.updatesCard(cardModel)
    }

    override suspend fun deleteCard(cardModel: CardModel) {
        cardRepository.deleteCard(cardModel)
    }
}