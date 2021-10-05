package br.com.programadorjm.businesscard.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.programadorjm.businesscard.App
import br.com.programadorjm.businesscard.R
import br.com.programadorjm.businesscard.databinding.FragmentListCardBinding
import br.com.programadorjm.businesscard.presenter.viewmodel.ListCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCardFragment : Fragment() {
    private val viewModelHilt:ListCardViewModel by viewModels()
    lateinit var binding: FragmentListCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListCardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_listCardFragment_to_newCardFragment)
        }
    }
}