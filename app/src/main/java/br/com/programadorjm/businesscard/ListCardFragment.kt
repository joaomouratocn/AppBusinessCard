package br.com.programadorjm.businesscard

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.programadorjm.businesscard.domain.usecases.UseCasesImpl
import br.com.programadorjm.businesscard.presenter.viewmodel.ListCardViewModel

class ListCardFragment : Fragment() {
    lateinit var viewModel:ListCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            viewModelStore,
            ListCardViewModel.ListCardViewModelFactory((activity?.application as App).useCasesImpl),
        ).get(ListCardViewModel::class.java)
    }
}