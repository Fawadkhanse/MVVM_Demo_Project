package com.appinsnap.lwmc.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.TextView
import com.appinsnap.lwmc.R


fun Context.showAlertDialog(stMessage: String?) {
    val dialog = Dialog(this)
    dialog.setCancelable(true)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    // val view = layoutInflater.inflate(R.layout.alert_dialog, null)
    dialog.setContentView(R.layout.alert_dialog)
    val Message: TextView
    val btnAllow: TextView
    Message = dialog.findViewById<View>(R.id.tvMessage) as TextView
    btnAllow = dialog.findViewById<View>(R.id.btnAllow) as TextView
    Message.text = stMessage
    btnAllow.setOnClickListener {
        dialog.dismiss()
    }
    dialog.show()
}

fun Context.showAlertDialogsWithButtonClicked(stMessage: String?, onclick: DialogClick, isCancel:Boolean= true) {
    val dialog = Dialog(this)
    dialog.setCancelable(isCancel)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    // val view = layoutInflater.inflate(R.layout.alert_dialog, null)
    dialog.setContentView(R.layout.alert_dialog)
    val Message: TextView
    val btnAllow: TextView
    Message = dialog.findViewById<View>(R.id.tvMessage) as TextView
    btnAllow = dialog.findViewById<View>(R.id.btnAllow) as TextView
    Message.text = stMessage
    btnAllow.setOnClickListener {
        dialog.dismiss()
        onclick.onYesClicked()
    }
    dialog.show()
}
fun Context.showPermissionDeniedDialog(stMessage: String?,flag : Boolean) {
    val dialog = Dialog(this)
    dialog.setCancelable(flag)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
     //val view = layoutInflater.inflate(R.layout.alert_dialog, null)
    dialog.setContentView(R.layout.alert_dialog)
    val Message: TextView
    val btnAllow: TextView
    Message = dialog.findViewById<View>(R.id.tvMessage) as TextView
    btnAllow = dialog.findViewById<View>(R.id.btnAllow) as TextView
    Message.text = stMessage
    btnAllow.setOnClickListener {
        dialog.dismiss()
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package",this.packageName, null)
        intent.data = uri
        startActivity(intent)
    }
    dialog.show()
}

fun Dialog.alertDialog(stMessage: String?) {
    this.setCancelable(true)
    this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    // val view = layoutInflater.inflate(R.layout.alert_dialog, null)
    this.setContentView(R.layout.alert_dialog)
    val message: TextView = this.findViewById<View>(R.id.tvMessage) as TextView
    val btnAllow: TextView = this.findViewById<View>(R.id.btnAllow) as TextView
    message.text = stMessage
    btnAllow.setOnClickListener {
        this.dismiss()

    }
    this.show()
}
fun Dialog.ifShowDismissed(){
    if (this.isShowing){
        this.dismiss()
    }
}

interface DialogClick{
    fun onYesClicked()
    fun onNoClicked(){}
}