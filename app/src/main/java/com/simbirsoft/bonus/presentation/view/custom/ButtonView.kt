package com.simbirsoft.bonus.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.ViewButtonBinding

class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewButtonBinding

    private val textColorInt by lazy { ContextCompat.getColor(context, R.color.colorPrimary) }
    private val disabledTextColorInt by lazy { ContextCompat.getColor(context, R.color.backgroundColor) }

    /**
     * Заголовок кнопки.
     */
    var title: String? = null
        set(value) {
            field = value
            binding.buttonTitleTextView.text = title
        }

    init {
        inflate(context, R.layout.view_button, this)
        binding = ViewButtonBinding.bind(this)
        applyAttributes(attrs, defStyleAttr)
        setBackgroundResource(R.drawable.button_background)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        applyTextColor()
    }

    private fun applyAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ButtonView,
            defStyleAttr = defStyleAttr,
        ) {
            title = getString(
                R.styleable.ButtonView_buttonTitle
            ) ?: title
        }
    }

    private fun applyTextColor() {
        val newTextColorInt = getTextColor()
        binding.buttonTitleTextView.setTextColor(newTextColorInt)
    }

    @ColorInt
    private fun getTextColor(): Int = if (isEnabled) disabledTextColorInt else textColorInt
}