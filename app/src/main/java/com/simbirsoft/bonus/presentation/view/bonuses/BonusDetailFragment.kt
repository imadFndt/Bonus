package com.simbirsoft.bonus.presentation.view.bonuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.simbirsoft.bonus.databinding.FragmentBonusDetailBinding
import com.simbirsoft.bonus.presentation.mainActivity
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.BonusDetailViewModel
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class BonusDetailFragment : Fragment() {

    companion object {
        const val TAG = "BonusDetailFragment"
        const val ARGUMENT_KEY = "item"

        fun newInstance(item: Item) = BonusDetailFragment().apply {
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

        mainActivity?.setBottomNavigationBarVisibility(isVisible = false)

        val item = requireArguments().getParcelable<Item>(ARGUMENT_KEY) ?: error("No Arg")
        binding.toolbar.isBackButtonVisible = true
        binding.toolbar.onBackPressedListener = {
            mainActivity?.popBackStack()
        }
        binding.titleTextView.text = item.text
        binding.descriptionTextView.text = "Уровни \n" +
                "\n" +
                "ДМС 1 уровня, которое включает поликлиническое обслуживание, вызов терапевта на дом, экстренная госпитализация, плановая госпитализация. Доступна для выбора сотрудников со стажем 1 год и более.\n" +
                "\n" +
                "ДМС 2 уровня, которое включает поликлиническое обслуживание, вызов терапевта на дом, экстренная госпитализация, плановая госпитализация и стоматология. Доступна для выбора сотрудников со стажем 2 года и более.\n" +
                "\n" +
                "Информация\n" +
                "\n" +
                "Страховая компания — АльфаСтрахование.\n" +
                "\n" +
                "На сотрудника выбравшего ДМС компания оформляет полис, который приходит на  корпоративную почту, за несколько дней до начала действия полиса. Также приходит приложение к полису — памятка, где находится список доступных клиник, описаны предоставляемые услуги и исключения. \n" +
                "\n" +
                "Рекомендуется установить мобильное приложение «АльфаСтрах», в котором вся информация будет в одном месте. Инструкция по установке мобильного приложения. "

        binding.wantThisBonusButton.setOnClickListener {
            viewModel.onWantBonusPressed()
        }
    }
}