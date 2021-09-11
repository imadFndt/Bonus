package com.simbirsoft.bonus.presentation.view.bonuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.FragmentBonusesBinding
import com.simbirsoft.bonus.presentation.mainActivity
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemAdapter
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemDecoration
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemDiffUtilCallback
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.BonusesViewModel
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusesFragment : Fragment() {

    companion object {
        const val TAG = "BonusesFragment"

        fun newInstance() = BonusesFragment()
    }

    private lateinit var binding: FragmentBonusesBinding
    private lateinit var itemsAdapter: BonusItemAdapter
    private val viewModel: BonusesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.setBottomNavigationBarVisibility(isVisible = true)

        binding.userHelloTextView.text = "Привет, Иван"

        itemsAdapter = BonusItemAdapter {
            openItemDetails(it)
        }
        binding.itemsRecyclerView.apply {
            adapter = itemsAdapter
            val spacing = resources.getDimensionPixelSize(R.dimen.margin_8dp)
            addItemDecoration(BonusItemDecoration(spacing))
        }

        observeViewModel()
    }

    private fun openItemDetails(item: Item) {
        mainActivity?.addFragment(BonusDetailFragment.newInstance(item))
    }

    private fun observeViewModel() {
        viewModel.items().observe(viewLifecycleOwner, ::renderItems)
    }

    private fun renderItems(items: List<Item>) {
        val callback = BonusItemDiffUtilCallback(
            oldItems = itemsAdapter.getItems(),
            newItems = items
        )
        val diffResult = DiffUtil.calculateDiff(callback)
        itemsAdapter.swapData(items)
        diffResult.dispatchUpdatesTo(itemsAdapter)
    }
}