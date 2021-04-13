package com.example.samplepoc.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.samplepoc.util.ConnectionType
import com.example.samplepoc.util.Logger.Companion.logite
import com.example.samplepoc.util.NetworkMonitorUtil
import com.example.samplepoc.util.Util
import com.example.samplepoc.viemodel.BaseVM

abstract class BaseActivity : AppCompatActivity() {

    var actCtx: Context? = null
    protected var pDlg: Dialog? = null
    protected var networkMonitor: NetworkMonitorUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        logite(this.javaClass.simpleName, ".onCreate")
        super.onCreate(savedInstanceState)
    }

    protected open fun setUpBase(actCtx: Context?, vm: BaseVM?) {
        this.actCtx = actCtx
        pDlg = Util.getPDlg(actCtx)

        if (vm == null) return

        vm.getShowProgress()?.observe(this, Observer { aBoolean ->
            if (aBoolean != null && aBoolean) pDlg!!.show() else pDlg!!.dismiss()
        })

        vm.getDialogMessage()!!.observe(this, Observer { s ->
            if (s == null) return@Observer
            AlertDialog.Builder(actCtx)
                .setMessage(s)
                .setPositiveButton(
                    "OK"
                ) { dialog: DialogInterface?, which: Int ->
                }.show()
            vm.setDialogConsumed()
        })

        networkMonitor = NetworkMonitorUtil(actCtx!!)
        networkMonitor!!.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                logite("Network Status", "Wifi Connection")
                            }
                            ConnectionType.Cellular -> {
                                logite("Network Status", "Cellular Connection")
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        vm.showDialog("No internet connection!")
                        logite("Network Status", "No Connection")
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        networkMonitor?.register()
        logite(this.javaClass.simpleName, "onResume")

    }

    override fun onStop() {
        super.onStop()
        networkMonitor?.unregister()
        logite(this.javaClass.simpleName, "onStop")
    }
}