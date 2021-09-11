package com.simbirsoft.bonus.presentation.view.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.StyleRes
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
     * Фон тулбара
     */
    var toolbarBackground: Drawable? = ContextCompat.getDrawable(context, R.color.backgroundColor)
        set(value) {
            field = value
            binding.root.background = value
        }

    /**
     * Видимость иконки "Назад"
     */
    var isBackButtonVisible: Boolean = false
        set(value) {
            field = value
            binding.toolbarBackButton.isVisible = value
        }

    /**
     * Действие по нажатию на иконки "Назад"
     */
    var onBackPressedListener: (() -> Unit)? = null

    /**
     * Текст заголовка тулбара
     */
    var title: String = ""
        set(value) {
            field = value
            binding.titleTextView.text = value
        }

    /**
     * Стиль заголовка
     */
    @StyleRes
    var titleTextAppearance: Int = R.style.TextStyle_Size32_Medium
        set(value) {
            field = value
            binding.titleTextView.setTextAppearance(context, value)
        }

    /**
     * Текст под заголовком тулбара
     */
    var description: String = ""
        set(value) {
            field = value
            binding.descriptionTextView.apply {
                text = value
                isVisible = value.isNotEmpty()
            }
        }

    /**
     * Стиль подписи под заголовком
     */
    @StyleRes
    var descriptionTextAppearance: Int = R.style.TextStyle_Size14
        set(value) {
            field = value
            binding.descriptionTextView.setTextAppearance(context, value)
        }

    /**
     * Иконка тулбара
     */
    var icon: Drawable? = null
        set(value) {
            field = value
            with(binding.toolbarIconButton) {
                value?.let { setImageDrawable(it) }
                isVisible = value != null
            }
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
     * Действие по нажатию на иконку
     */
    var onIconPressedListener: (() -> Unit)? = null

    init {
        inflate(context, R.layout.view_toolbar, this)
        binding = ViewToolbarBinding.bind(this)
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
            toolbarBackground = getDrawable(
                R.styleable.ToolbarView_toolbarBackground
            ) ?: toolbarBackground
            isBackButtonVisible = getBoolean(
                R.styleable.ToolbarView_isBackButtonVisible,
                isBackButtonVisible
            )
            title = getString(
                R.styleable.ToolbarView_title
            ) ?: title
            titleTextAppearance = getResourceId(
                R.styleable.ToolbarView_titleTextAppearance,
                titleTextAppearance
            )
            description = getString(
                R.styleable.ToolbarView_description
            ) ?: description
            descriptionTextAppearance = getResourceId(
                R.styleable.ToolbarView_descriptionTextAppearance,
                descriptionTextAppearance
            )
            icon = getDrawable(R.styleable.ToolbarView_icon)
        }
    }
}
