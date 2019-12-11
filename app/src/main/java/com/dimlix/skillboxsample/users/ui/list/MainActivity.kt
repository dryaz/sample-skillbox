package com.dimlix.skillboxsample.users.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimlix.skillboxsample.BuildConfig
import com.dimlix.skillboxsample.R
import com.dimlix.skillboxsample.users.data.UserApi
import com.dimlix.skillboxsample.users.data.UserListResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var _userApi: UserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()

        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        httpClientBuilder.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(httpClientBuilder.build())
            .build()

        _userApi = retrofit.create(UserApi::class.java)

        _userApi.getUsers().enqueue(object : Callback<UserListResponse> {
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<UserListResponse>,
                listResponse: Response<UserListResponse>
            ) {
                listItems.adapter =
                    DataAdapter(listResponse.body()!!.data)
            }

        })
    }
}
