package com.dimlix.skillboxsample.users.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users")
    fun getUsers(): Call<UserListResponse>

    @GET("users/{id}")
    fun getUserDetails(@Path("id") id: Int): Call<UserResponse>
}