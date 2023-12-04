package com.example.homework1.ui.dialogs

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.homework1.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NewTaskDialog (context: Context, onPositiveButtonClick: (String) -> Unit){
    private val alertDialog: AlertDialog = MaterialAlertDialogBuilder(context).create()

    init {
        val editText = EditText(context)
        alertDialog.apply {
            setTitle(context.getString(R.string.add_new_task))
            setView(editText)
            setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
                val enteredText = editText.text.toString()
                onPositiveButtonClick.invoke(enteredText)
            }
            setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.dismiss)) { dialog, _ ->
                dialog.dismiss()
            }
        }
    }

    fun show() {
        alertDialog.show()
    }
}