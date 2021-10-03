package br.com.programadorjm.businesscard.data

import br.com.programadorjm.businesscard.domain.model.CardModel

interface CardRepository {
    suspend fun getAllCards():List<CardModel>

    suspend fun insertCard(cardModel: CardModel)

    suspend fun updatesCard(cardModel: CardModel)

    suspend fun deleteCard(cardModel: CardModel)
}