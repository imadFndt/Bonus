package com.simbirsoft.bonus.presentation.view.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.simbirsoft.bonus.databinding.FragmentTimeLineBinding
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel
import com.simbirsoft.bonus.presentation.navigationListener
import com.simbirsoft.bonus.presentation.viewmodel.timeline.TimeLineViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeLineFragment : Fragment() {

    private lateinit var binding: FragmentTimeLineBinding
    private val viewModel: TimeLineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimeLineBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationListener?.setBottomNavigationBarVisibility(isVisible = true)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getScreenModel().observe(viewLifecycleOwner, ::render)
    }

    private fun render(model: TimeLineScreenModel) {
        binding.timeline.replaceLvls(model.lvls)
        binding.toolbar.title = model.title
        binding.toolbar.isIconVisible = model.countBonus != COUNT_BONUS_NULL
        if (model.countBonus != COUNT_BONUS_NULL) {
            binding.toolbar.isIconVisible = true
            binding.toolbar.onIconPressedListener = {
                navigationListener?.showPopUpWindow()
            }
        }
    }

    companion object {
        const val TAG = "TimeLineFragment"
        private const val COUNT_BONUS_NULL = 0

        @JvmStatic
        fun newInstance() = TimeLineFragment()
    }
}