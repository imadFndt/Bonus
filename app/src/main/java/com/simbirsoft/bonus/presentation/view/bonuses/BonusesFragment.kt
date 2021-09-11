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
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.presentation.mainActivity
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemAdapter
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemDecoration
import com.simbirsoft.bonus.presentation.view.bonuses.recyclerview.BonusItemDiffUtilCallback
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.BonusesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusesFragment : Fragment() {

    companion object {
        const val TAG = "BonusesFragment"
        const val TYPE_ARG = "TYPE_ARG"

        fun newInstance(type: BonusType? = null) = BonusesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TYPE_ARG, type)
            }
        }
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

        binding.toolbar.title = "Привет, Иван"

        itemsAdapter = BonusItemAdapter {
            openItemDetails(it)
        }
        binding.itemsRecyclerView.apply {
            adapter = itemsAdapter
            val spacing = resources.getDimensionPixelSize(R.dimen.margin_8dp)
            addItemDecoration(BonusItemDecoration(spacing))
        }
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.thingsChip -> viewModel.changeSelectedType(BonusType.MERCH)
                R.id.activitiesChip -> viewModel.changeSelectedType(BonusType.ACTIVITY)
                R.id.bonusesChip -> viewModel.changeSelectedType(BonusType.BONUS)
            }
        }

        selectDefaultTypeIfPresent()
        observeViewModel()
    }

    private fun selectDefaultTypeIfPresent() {
        arguments?.getParcelable<BonusType>(TYPE_ARG)?.let { type ->
            when(type) {
                BonusType.MERCH -> binding.thingsChip.isChecked = true
                BonusType.ACTIVITY -> binding.activitiesChip.isChecked = true
                BonusType.BONUS -> binding.bonusesChip.isChecked = true
            }
            viewModel.changeSelectedType(type)
        }
    }

    private fun openItemDetails(item: Bonus) {
        mainActivity?.addFragment(binding.root, BonusDetailFragment.newInstance(item))
    }

    private fun observeViewModel() {
        viewModel.itemsState().observe(viewLifecycleOwner, ::renderItems)
    }

    private fun renderItems(items: List<Bonus>) {
        val callback = BonusItemDiffUtilCallback(
            oldItems = itemsAdapter.getItems(),
            newItems = items
        )
        val diffResult = DiffUtil.calculateDiff(callback)
        itemsAdapter.swapData(items)
        diffResult.dispatchUpdatesTo(itemsAdapter)
    }
}