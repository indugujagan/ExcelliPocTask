package com.example.samplepoc.util

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.samplepoc.R

class Util {

    companion object {

        private var pDlg: Dialog? = null
        fun getPDlg(ctx: Context?): Dialog? {
            pDlg = Dialog(ctx!!, android.R.style.Theme_Translucent_NoTitleBar)
            pDlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            pDlg!!.setContentView(R.layout.progress_dialog_el)
            pDlg!!.setCancelable(false)
            return pDlg
        }

    }

}