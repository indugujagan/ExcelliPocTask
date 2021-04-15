package com.example.samplepoc.interfaces

import android.view.View
import com.example.samplepoc.model.Search

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClick(view: View, movieItem: Search)
}