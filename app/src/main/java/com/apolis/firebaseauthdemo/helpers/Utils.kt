package com.apolis.firebaseauthdemo.helpers

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String){
    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}

fun snackBar(view: View){
    val snackBar = Snackbar.make(view, "xxx",
        Snackbar.LENGTH_LONG).setAction("Action", null)
    snackBar.setActionTextColor(Color.BLUE)
    val snackbarView = snackBar.view
    snackbarView.setBackgroundColor(Color.LTGRAY)
    val textView =
        snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.BLUE)
    textView.textSize = 28f
    snackBar.show()
}