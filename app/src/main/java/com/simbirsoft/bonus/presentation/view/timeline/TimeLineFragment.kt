package com.simbirsoft.bonus.presentation.view.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.simbirsoft.bonus.databinding.FragmentTimeLineBinding
import com.simbirsoft.bonus.presentation.mainActivity
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

        mainActivity?.setBottomNavigationBarVisibility(isVisible = true)

        binding.toolbar.title = "В полете 2 год и 2 мес"

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.items().observe(viewLifecycleOwner, ::renderItems)
    }

    private fun renderItems(items: List<TimeLineLevelModel>) {
        binding.timeline.replaceLvls(items)
    }

    companion object {
        const val TAG = "TimeLineFragment"

        @JvmStatic
        fun newInstance() = TimeLineFragment()
    }
}