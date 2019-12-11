package com.dimlix.skillboxsample.users.data

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatarUrl: String
)