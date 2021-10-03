package br.com.programadorjm.businesscard.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.domain.usecases.UseCasesImpl
import br.com.programadorjm.businesscard.util.EnumOperation
import java.lang.IllegalArgumentException

class ListCardViewModel(private val useCasesImpl: UseCasesImpl):ViewModel() {

    private val cardState = MutableLiveData<CardState>()
    val cardStateView:LiveData<CardState> = cardState

    sealed class CardState{
        class GetAllCards(private val listCard:List<CardModel>, operation:EnumOperation):CardState()
        class DeleteCard(private val cardModel: CardModel):CardState()
    }

    class ListCardViewModelFactory(private val useCasesImpl: UseCasesImpl):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListCardViewModel::class.java)){
                return modelClass.getConstructor(UseCasesImpl::class.java).newInstance(useCasesImpl)
            }
            throw IllegalArgumentException("Unknown class")
        }

    }
}