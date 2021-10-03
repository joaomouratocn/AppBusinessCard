package br.com.programadorjm.businesscard.domain.usecases

import br.com.programadorjm.businesscard.domain.model.CardModel

interface UseCases {
    suspend fun getAllCards():List<CardModel>

    suspend fun insertCard(cardModel: CardModel)

    suspend fun updateCard(cardModel: CardModel)

    suspend fun deleteCard(cardModel: CardModel)
}