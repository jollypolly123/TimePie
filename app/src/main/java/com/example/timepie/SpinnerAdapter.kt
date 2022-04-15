package com.example.timepie

import android.app.Activity
import android.view.View
import android.widget.AdapterView

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}