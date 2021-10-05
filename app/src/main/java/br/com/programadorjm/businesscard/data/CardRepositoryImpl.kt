package br.com.programadorjm.businesscard.data

import br.com.programadorjm.businesscard.data.room.CardDao
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.util.toCardEntity
import br.com.programadorjm.businesscard.util.toListCardModel
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardDao: CardDao):CardRepository {
    override suspend fun getAllCards(): List<CardModel> {
        return cardDao.getAllCards().toListCardModel()
    }

    override suspend fun insertCard(cardModel: CardModel) {
        cardDao.insertCard(cardModel.toCardEntity())
    }

    override suspend fun updatesCard(cardModel: CardModel) {
        cardDao.updateCard(cardModel.toCardEntity())
    }

    override suspend fun deleteCard(cardModel: CardModel) {
        cardDao.deleteCard(cardModel.toCardEntity())
    }
}