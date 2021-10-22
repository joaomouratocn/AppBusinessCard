package br.com.programadorjm.businesscard.presenter.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.programadorjm.businesscard.R
import br.com.programadorjm.businesscard.databinding.FragmentListCardBinding
import br.com.programadorjm.businesscard.domain.model.CardModel
import br.com.programadorjm.businesscard.presenter.adapter.CardAdapter
import br.com.programadorjm.businesscard.presenter.viewmodel.MainViewModel
import br.com.programadorjm.businesscard.util.ImageCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCardFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentListCardBinding
    private lateinit var cardAdapter: CardAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

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
        cardAdapter = CardAdapter { cardClickListener(it) }


        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(0, ItemTouchHelper.LEFT))
        itemTouchHelper.attachToRecyclerView(binding.recycleCards)

        binding.recycleCards.adapter = cardAdapter

        binding.floatAddCard.setOnClickListener {
            navigate(getString(R.string.str_new_card))
        }

        mainViewModel.cardStateView.observe(viewLifecycleOwner){ cardState ->
            when(cardState){
                is MainViewModel.CardState.GetAllCards ->{
                    cardAdapter.submitList(cardState.listCard)
                    cardAdapter.notifyDataSetChanged()
                }else ->{ Log.e("Error state", "Invalid card state")}
            }
        }

        setupPermissions()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.getAllCards()
    }

    private fun cardClickListener(cardModel: CardModel){
        mainViewModel.loadCard(cardModel)
        navigate(getString(R.string.str_update_card))
    }

    private fun navigate(toolbarTitle:String){
        val directions = ListCardFragmentDirections.actionListCardFragmentToNewCardFragment(toolbarTitle)
        findNavController().navigate(directions)
    }

    private fun setupPermissions(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

    inner class ItemTouchHelperCallback(dragDirs:Int, swipeDirs:Int): ItemTouchHelper.SimpleCallback(dragDirs,swipeDirs){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when(direction){
                ItemTouchHelper.LEFT -> {
                    ImageCard.shareCardImage(requireContext(), viewHolder.itemView)
                    cardAdapter.notifyDataSetChanged()
                }
            }
        }

    }
}