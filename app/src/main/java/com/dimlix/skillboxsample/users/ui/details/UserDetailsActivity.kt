package com.dimlix.skillboxsample.users.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dimlix.skillboxsample.R
import com.dimlix.skillboxsample.users.data.UserApi
import com.dimlix.skillboxsample.users.ui.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var _userApi: UserApi

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val viewModel: UserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        viewModel.loadSingleUserData(intent.getIntExtra(UserDetailsActivity.USER_ID, -1))
        viewModel.userData.observe(this, Observer {
            tvName.text = "${it.firstName} ${it.lastName}"
            tvEmail.text = it.email
            Picasso.get().load(it.avatarUrl).into(imgAvatar)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val USER_ID = "uidkey"

        fun start(context: Context, userId: Int) {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(USER_ID, userId)
            context.startActivity(intent)
        }
    }
}
