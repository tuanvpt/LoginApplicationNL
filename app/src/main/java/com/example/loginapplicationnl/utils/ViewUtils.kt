package com.example.loginapplicationnl.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Utilities of View
 */
object ViewUtils {
    /**
     * Change visibility of view to Visible
     *
     * @param view   view need to change visibility
     * @param isShow is showing view or not
     */
    fun changeVisibility(view: View, isShow: Boolean) {
        if (isShow) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    /**
     * Show dialog
     *
     * @param dialog dialog need to show
     */
    fun showDialog(dialog: Dialog) {
        /*Only show if dialog not showing*/
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    /**
     * Hide keyboard
     *
     * @param context context
     * @param view    view need focus
     */
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}