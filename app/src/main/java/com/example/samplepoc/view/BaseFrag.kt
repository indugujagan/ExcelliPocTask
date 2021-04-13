package com.example.samplepoc.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.samplepoc.util.ConnectionType
import com.example.samplepoc.util.Logger
import com.example.samplepoc.util.NetworkMonitorUtil
import com.example.samplepoc.util.Util
import com.example.samplepoc.viemodel.BaseVM

abstract class BaseFrag : Fragment() {

    protected var actCtx: Context? = null
    protected var pDlg: Dialog? = null
    private var networkMonitor: NetworkMonitorUtil? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actCtx = activity
        networkMonitor = NetworkMonitorUtil(actCtx!!)
        val config = resources.configuration
        Logger.logite(this.javaClass.simpleName + ".onCreate", "densityDpi=" + config.densityDpi)
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
            requireActivity().runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Logger.logite("Network Status", "Wifi Connection")
                            }
                            ConnectionType.Cellular -> {
                                Logger.logite("Network Status", "Cellular Connection")
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        vm.showDialog("No internet connection!")
                        Logger.logite("Network Status", "No Connection")
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        networkMonitor?.register()
        Logger.logite(this.javaClass.simpleName, "onResume")

    }

    override fun onStop() {
        super.onStop()
        networkMonitor?.unregister()
        Logger.logite(this.javaClass.simpleName, "onStop")
    }

    abstract fun setupUI()
    abstract fun setupViewModel()
    abstract fun setupObserver()
}