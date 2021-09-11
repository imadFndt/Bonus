package com.simbirsoft.bonus.presentation.view.login

import android.text.Editable
import android.text.TextWatcher

class LoginTextWatcher(
    private val afterTextChanged: (text: Editable?) -> Unit
): TextWatcher {

    override fun beforeTextChanged(
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) = Unit

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) = Unit

    override fun afterTextChanged(editable: Editable?) {
        afterTextChanged.invoke(editable)
    }
}