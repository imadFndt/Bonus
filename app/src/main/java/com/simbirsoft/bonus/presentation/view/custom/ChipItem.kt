package com.simbirsoft.bonus.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.ItemChipBinding

class ChipItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ItemChipBinding

    var title: String = ""
        set(value) {
            field = value
            binding.chipTitle.text = value
        }

    var onChipSelected: (() -> Unit)? = null

    @ColorRes
    private var textColor: Int = R.color.primaryTextColor
        set(value) {
            field = value
            binding.chipTitle.setTextColor(ContextCompat.getColor(context, value))
        }

    @ColorRes
    private var chipBackgroundColor: Int = R.color.chip_inactive_color
        set(value) {
            field = value
            setCardBackgroundColor(ContextCompat.getColor(context, value))
        }

    init {
        inflate(context, R.layout.item_chip, this)
        binding = ItemChipBinding.bind(this)
        radius = resources.getDimensionPixelSize(R.dimen.margin_12dp).toFloat()
        binding.root.setOnClickListener {
            onChipSelected?.invoke()
        }
        applyAttributes(attrs, defStyleAttr)
    }

    fun setChipSelected(isSelected: Boolean) {
        if (isSelected) {
            textColor = R.color.backgroundColor
            chipBackgroundColor = R.color.colorPrimary
        } else {
            textColor = R.color.colorPrimary
            chipBackgroundColor = R.color.chip_inactive_color
        }
    }

    private fun applyAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ChipItem,
            defStyleAttr = defStyleAttr,
        ) {
            title = getString(
                R.styleable.ChipItem_chipTitle
            ) ?: title
        }
    }
}