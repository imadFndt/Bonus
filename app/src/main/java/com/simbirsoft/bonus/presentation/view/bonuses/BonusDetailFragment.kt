package com.simbirsoft.bonus.presentation.view.bonuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.simbirsoft.bonus.databinding.FragmentBonusDetailBinding
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.presentation.navigationListener
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.BonusDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusDetailFragment : Fragment() {

    companion object {
        const val TAG = "BonusDetailFragment"
        const val ARGUMENT_KEY = "item"

        fun newInstance(item: Bonus) = BonusDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_KEY, item)
            }
        }
    }

    private lateinit var binding: FragmentBonusDetailBinding
    private val viewModel: BonusDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationListener?.setBottomNavigationBarVisibility(isVisible = false)

        val item = requireArguments().getParcelable<Bonus>(ARGUMENT_KEY) ?: error("No Arg")
        binding.toolbar.onBackPressedListener = {
            activity?.onBackPressed()
        }
        binding.bonusTitleTextView.text = item.title
        binding.bonusDescriptionTextView.text = item.description

        binding.wantThisBonusButton.setOnClickListener {
            viewModel.onWantBonusPressed()
        }
    }
}