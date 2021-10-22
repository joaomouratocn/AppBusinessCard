package br.com.programadorjm.businesscard.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: UseCases):ViewModel() {
    private val cardState = MutableLiveData<CardState>()
    val cardStateView:LiveData<CardState> = cardState

    sealed class CardState{
        class GetAllCards(val listCard:List<CardModel>):CardState()
        class UpdateClass(val cardUpdate:CardModel):CardState()
    }

    fun cardInsert(cardModel: CardModel) {
        viewModelScope.launch {
            useCases.insertCard(cardModel)
            cardState.value = CardState.GetAllCards(useCases.getAllCards())
        }
    }

    fun updateCard(cardModel: CardModel) {
        viewModelScope.launch {
            useCases.updateCard(cardModel)
            cardState.value = CardState.GetAllCards(useCases.getAllCards())
        }
    }

    fun loadCard(cardModel: CardModel) {
        cardState.value = CardState.UpdateClass(cardModel)
    }

    fun getAllCards(){
        viewModelScope.launch { cardState.value = CardState.GetAllCards(useCases.getAllCards()) }
    }

}
