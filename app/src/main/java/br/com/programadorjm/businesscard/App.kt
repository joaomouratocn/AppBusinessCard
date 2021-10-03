package br.com.programadorjm.businesscard

import android.app.Application
import br.com.programadorjm.businesscard.data.CardRepositoryImpl
import br.com.programadorjm.businesscard.data.room.CardDatabase

class App:Application() {
    private val cardDatabase by lazy { CardDatabase.getDataBase(this) }
    val cardRepositoryImpl by lazy { CardRepositoryImpl(cardDatabase.cardDao()) }
}