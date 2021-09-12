package com.simbirsoft.bonus.presentation.view.bonuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.FragmentBonusDetailBinding
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusInfo
import com.simbirsoft.bonus.domain.ext.sendEmail
import com.simbirsoft.bonus.presentation.mapper.Emoji.emoji
import com.simbirsoft.bonus.presentation.mapper.Emoji.firstLayer
import com.simbirsoft.bonus.presentation.navigationListener
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.BonusDetailViewModel
import com.simbirsoft.bonus.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class BonusDetailFragment : Fragment() {

    companion object {
        const val TAG = "BonusDetailFragment"
        const val ARGUMENT_KEY = "item"
        const val EMAIL_DEFAULT = "artem.efimov@simbirsoft.com"

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
        viewModel.bonus = item
        binding.toolbar.onBackPressedListener = {
            activity?.onBackPressed()
        }
        binding.itemIcon.setImageDrawable(item.firstLayer(requireContext()))
        binding.emojiTextView.text = item.emoji()
        binding.bonusTitleTextView.text = item.title
        binding.bonusDescriptionTextView.text = item.description

        binding.wantThisBonusButton.isEnabled = true
        binding.wantThisBonusButton.setOnClickListener {
            viewModel.onWantBonusPressed()
        }

        binding.wantThisBonusButton.title = viewModel.getButtonTitle()

        viewModel.successState().observeEvent(viewLifecycleOwner) { info ->
            renderBonusInfo(info)
        }
    }

    private fun renderBonusInfo(info: BonusInfo) {
        if (info.canGetBonus) {
            requireContext().sendEmail(
                email = EMAIL_DEFAULT,
                chooserTitle = resources.getString(R.string.want_this_bonus_text),
                body = info.emailText + viewModel.bonus?.title.orEmpty()
            )
            Toasty.success(requireContext(), info.errorText, Toast.LENGTH_SHORT, true).show()
        } else {
            Toasty.info(requireContext(), info.errorText, Toast.LENGTH_SHORT, true).show()
        }
    }
}