package com.simbirsoft.bonus.presentation.view.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.databinding.ViewToolbarBinding

/**
 * Элемент тулбара с текстом, кнопкой назад и иконкой справа
 */
class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewToolbarBinding

    /**
     * Видимость иконки "Назад"
     */
    var isBackButtonVisible: Boolean = false
        set(value) {
            field = value
            binding.toolbarBackButton.isVisible = value
        }

    /**
     * Текст тулбара
     */
    var title: String = ""
        set(value) {
            field = value
            binding.title.text = value
        }

    /**
     * Видимость иконки тулбара
     */
    var isIconVisible: Boolean = true
        set(value) {
            field = value
            binding.toolbarIconButton.isVisible = value
        }

    /**
     * Действие по нажатию на иконки "Назад"
     */
    var onBackPressedListener: (() -> Unit)? = null

    /**
     * Действие по нажатию на иконку
     */
    var onIconPressedListener: (() -> Unit)? = null

    /**
     * Иконка тулбара
     */
    private var icon: Drawable? = null
        set(value) {
            field = value
            with(binding.toolbarIconButton) {
                value?.let { setImageDrawable(it) }
                isVisible = value != null
            }
        }

    init {
        inflate(context, R.layout.view_toolbar, this)
        binding = ViewToolbarBinding.bind(this)
        binding.root.background = ContextCompat.getDrawable(context, R.color.backgroundColor)
        binding.root.elevation = context.resources.getDimensionPixelSize(R.dimen.margin_4dp).toFloat()
        binding.toolbarBackButton.setOnClickListener {
            onBackPressedListener?.invoke()
        }
        binding.toolbarIconButton.setOnClickListener {
            onIconPressedListener?.invoke()
        }
        applyAttributes(attrs, defStyleAttr)
    }

    private fun applyAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ToolbarView,
            defStyleAttr = defStyleAttr,
        ) {
            title = getString(R.styleable.ToolbarView_title).orEmpty()
            icon = getDrawable(R.styleable.ToolbarView_icon)
        }
    }
}
