package com.example.samplepoc.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseVM : ViewModel() {

    private var showProgress = MutableLiveData<Boolean>()
    private var dialogMessage = MutableLiveData<String>()

    open fun showDialog(message: String?) {
        dialogMessage.postValue(message)
    }

    open fun showDialog(t: Throwable) {
        dialogMessage.postValue(t.message)
    }

    open fun getDialogMessage(): LiveData<String?>? {
        return dialogMessage
    }

    open fun setDialogConsumed() {
        dialogMessage.postValue(null)
    }

    open fun setShowProgress(value: Boolean?) {
        showProgress.postValue(value)
    }

    open fun getShowProgress(): LiveData<Boolean?>? {
        return showProgress
    }
}