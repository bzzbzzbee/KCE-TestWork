package com.bzzbzzbee.kcetestwork.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.addTextWatcher(
    afterTextChanged: (String) -> Unit
) {
    this.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            afterTextChanged(this@addTextWatcher.editText?.text.toString())
        }
    })
}