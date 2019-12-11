package com.dimlix.skillboxsample.users.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dimlix.skillboxsample.BuildConfig
import com.dimlix.skillboxsample.R
import com.dimlix.skillboxsample.users.data.UserApi
import com.dimlix.skillboxsample.users.data.UserResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var _userApi: UserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()

        val interceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        httpClientBuilder.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(httpClientBuilder.build())
            .build()

        _userApi = retrofit.create(UserApi::class.java)

        _userApi.getUserDetails(intent.getIntExtra(USER_ID, -1))
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@UserDetailsActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    val data = response.body()?.data
                    tvName.text = "${data?.firstName} ${data?.lastName}"
                    tvEmail.text = "${data?.email}"
                    Picasso.get().load(data?.avatarUrl).into(imgAvatar)
                }

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
