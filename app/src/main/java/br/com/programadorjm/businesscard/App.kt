package br.com.programadorjm.businesscard

import android.app.Application
import br.com.programadorjm.businesscard.data.CardRepositoryImpl
import br.com.programadorjm.businesscard.data.room.CardDatabase
import br.com.programadorjm.businesscard.domain.usecases.UseCasesImpl

class App:Application() {
    private val cardDatabase by lazy { CardDatabase.getDataBase(this) }
    private val cardRepositoryImpl by lazy { CardRepositoryImpl(cardDatabase.cardDao()) }
    val useCasesImpl by lazy { UseCasesImpl(cardRepositoryImpl) }
}