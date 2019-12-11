package com.dimlix.skillboxsample.users.data

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}