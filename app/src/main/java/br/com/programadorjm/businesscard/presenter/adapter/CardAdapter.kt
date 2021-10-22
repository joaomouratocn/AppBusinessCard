package br.com.programadorjm.businesscard.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.programadorjm.businesscard.databinding.ItemLayoutCardBinding
import br.com.programadorjm.businesscard.domain.model.CardModel

class CardAdapter(private val cardClickListener: (CardModel) -> Unit) :ListAdapter<CardModel, CardAdapter.CardViewHolder>(CardModelCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.layoutInflate(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), cardClickListener)
    }

    class CardViewHolder(private val binding:ItemLayoutCardBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun layoutInflate(viewGroup: ViewGroup):CardViewHolder{
                val itemLayoutCardBinding = ItemLayoutCardBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
                return CardViewHolder(itemLayoutCardBinding)
            }
        }

        fun bind(cardModel: CardModel, cardClickListener: (CardModel) -> Unit){
            binding.card = cardModel
            binding.cvContentCard.setOnClickListener { cardClickListener(cardModel) }
        }
    }

    class CardModelCallBack:DiffUtil.ItemCallback<CardModel>(){
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.cardId == newItem.cardId
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.cardName == newItem.cardName &&
                    oldItem.cardPhone == newItem.cardPhone &&
                    oldItem.cardEmail == newItem.cardEmail &&
                    oldItem.cardCompany == newItem.cardCompany
        }

    }
}