package br.com.programadorjm.businesscard.util

import br.com.programadorjm.businesscard.data.room.CardEntity
import br.com.programadorjm.businesscard.domain.model.CardModel

fun CardEntity.toCardModel():CardModel{
    return CardModel(cardId, cardName, cardPhoneNumber, cardEmail,cardCompany)
}

fun CardModel.toCardEntity():CardEntity{
    return CardEntity(cardId, cardName, cardPhone, cardEmail, cardCompany)
}

fun List<CardEntity>.toListCardModel():List<CardModel>{
    val listCardModel = mutableListOf<CardModel>()
    this.forEach { listCardModel.add(it.toCardModel()) }
    return listCardModel
}