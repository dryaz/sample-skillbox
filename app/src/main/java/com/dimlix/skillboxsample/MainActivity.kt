package com.dimlix.skillboxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listItems.adapter = DataAdapter(getData())
    }

    fun getData(): List<String> {
        val dataSize = 100
        val data = mutableListOf<String>()
        for (index in 0..dataSize) {
            data.add("Item #$index")
        }
        return data
    }
}
