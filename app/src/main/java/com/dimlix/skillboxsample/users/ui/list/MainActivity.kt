package com.dimlix.skillboxsample.users.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimlix.skillboxsample.R
import com.dimlix.skillboxsample.users.ui.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: UserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        viewModel.userListData.observe(this, Observer {
            listItems.adapter = DataAdapter(it)
        })
        viewModel.loadUserList()

        listItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
