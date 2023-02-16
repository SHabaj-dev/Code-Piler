package com.sbz.code_piler.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.sbz.code_piler.*
import java.util.regex.Pattern

class CodeHighlighter {

    fun highlightCCode(code: String): SpannableString {
        val spannableString = SpannableString(code)
        for (keyword in C_KEYWORDS) {
            val pattern = Pattern.compile("\\b$keyword\\b")
            val matcher = pattern.matcher(spannableString)
            while (matcher.find()) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }

    fun highlightCPPCode(code: String): SpannableString {
        val spannableString = SpannableString(code)
        for (keyword in CPP_KEYWORDS) {
            val pattern = Pattern.compile("\\b$keyword\\b")
            val matcher = pattern.matcher(spannableString)
            while (matcher.find()) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }

    fun highlightJavaCode(code: String): SpannableString {
        val spannableString = SpannableString(code)
        for (keyword in JAVA_KEYWORDS) {
            val pattern = Pattern.compile("\\b$keyword\\b")
            val matcher = pattern.matcher(spannableString)
            while (matcher.find()) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }

    fun highlightPythonCode(code: String): SpannableString {
        val spannableString = SpannableString(code)
        for (keyword in PYTHON_KEYWORDS) {
            val pattern = Pattern.compile("\\b$keyword\\b")
            val matcher = pattern.matcher(spannableString)
            while (matcher.find()) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }
}