package br.com.programadorjm.businesscard.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.domain.usecases.UseCases
import br.com.programadorjm.businesscard.domain.usecases.UseCasesImpl
import br.com.programadorjm.businesscard.util.EnumOperation
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class ListCardViewModel @Inject constructor(private val useCases: UseCases):ViewModel() {

    private val cardState = MutableLiveData<CardState>()
    val cardStateView:LiveData<CardState> = cardState

    sealed class CardState{
        class GetAllCards(private val listCard:List<CardModel>, operation:EnumOperation):CardState()
        class DeleteCard(private val cardModel: CardModel):CardState()
    }
}