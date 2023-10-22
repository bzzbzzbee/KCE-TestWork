package com.bzzbzzbee.kcetestwork.utils

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View

object LinkCreator {
    fun createLink(
        text: String,
        links: Map<String, () -> Unit>
    ): SpannableStringBuilder {
        val spannableBuilder = SpannableStringBuilder(text)
        links.forEach { link ->
            val index = text.indexOf(link.key)

            spannableBuilder.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        link.value()
                    }
                },
                index, index + link.key.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        for (u in spannableBuilder
            .getSpans(
                0,
                spannableBuilder.length,
                ClickableSpan::class.java
            )
        ) {
            spannableBuilder.setSpan(
                object : UnderlineSpan() {
                    override fun updateDrawState(tp: TextPaint) {
                        tp.isUnderlineText = true
                    }
                },
                spannableBuilder.getSpanStart(u), spannableBuilder.getSpanEnd(u), 0
            )
        }

        return spannableBuilder
    }

    fun createLink(
        text: String,
        link: Pair<String, () -> Unit>
    ): SpannableStringBuilder {
        return createLink(text, mapOf(link))
    }
}