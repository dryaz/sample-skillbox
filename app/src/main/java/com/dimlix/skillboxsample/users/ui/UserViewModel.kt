package com.dimlix.skillboxsample.users.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dimlix.skillboxsample.BuildConfig
import com.dimlix.skillboxsample.users.data.UserApi
import com.dimlix.skillboxsample.users.data.UserData
import com.dimlix.skillboxsample.users.data.UserListResponse
import com.dimlix.skillboxsample.users.data.UserResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel(app: Application): AndroidViewModel(app) {

    private val _userApi: UserApi

    val userListData: MutableLiveData<List<UserData>> = MutableLiveData()
    val userData: MutableLiveData<UserData> = MutableLiveData()

    init {
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
    }

    fun loadUserList() {
        _userApi.getUsers().enqueue(object : Callback<UserListResponse> {
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                // TODO send error back
            }

            override fun onResponse(
                call: Call<UserListResponse>,
                listResponse: Response<UserListResponse>
            ) {
                userListData.value = listResponse.body()?.data
            }

        })
    }

    fun loadSingleUserData(id: Int) {
        _userApi.getUserDetails(id)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    // TODO send error back
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    userData.value = response.body()?.data
                }

            })
    }

}