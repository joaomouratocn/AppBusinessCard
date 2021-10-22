package br.com.programadorjm.businesscard.presenter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.programadorjm.businesscard.R
import br.com.programadorjm.businesscard.databinding.FragmentNewCardBinding
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.presenter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCardFragment : Fragment() {
    private lateinit var binding:FragmentNewCardBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.cardStateView.observe(viewLifecycleOwner) { cardState ->
            when (cardState) {
                is MainViewModel.CardState.UpdateClass -> {
                    binding.card = cardState.cardUpdate
                    binding.btnInsert.text = getText(R.string.str_save)
                }else -> { Log.e("State error", "Invalid Card State") }
            }
        }

        binding.btnInsert.setOnClickListener {
            val cardModel = CardModel(
                binding.card?.cardId ?: 0L,
                binding.edtName.text.toString(),
                binding.edtPhone.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtCompany.text.toString()
            )

            if (cardModel.cardId == 0L){insertCard(cardModel)}
            else{updateCard(cardModel)}
            findNavController().popBackStack()
        }
    }

    private fun insertCard(cardModel: CardModel){
        mainViewModel.cardInsert(cardModel)
    }

    private fun updateCard(cardModel: CardModel){
        mainViewModel.updateCard(cardModel)
    }
}