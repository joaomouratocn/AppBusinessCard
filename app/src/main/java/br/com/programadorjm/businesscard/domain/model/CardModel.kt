package br.com.programadorjm.businesscard.domain.model

data class CardModel(
    val cardId:Long = 0L,
    val cardName:String,
    val cardPhone:String,
    val cardEmail:String,
    val cardCompany:String
)