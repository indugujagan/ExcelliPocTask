package com.example.samplepoc.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.samplepoc.R
import com.example.samplepoc.viemodel.MainActivityVM

class MainActivity : BaseActivity() {

    private lateinit var vm: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this).get(MainActivityVM::class.java)
        setUpBase(this, vm)
    }
}