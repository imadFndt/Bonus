package com.simbirsoft.bonus.presentation.view.custom

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.simbirsoft.bonus.R

class LoaderDialog: DialogFragment() {

    companion object {
        const val TAG = "LoaderDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        isCancelable = false

        return inflater.inflate(R.layout.dialog_loader, container, false)
    }
}