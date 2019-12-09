package com.dimlix.skillboxsample

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatart")
    val avatarUrl: String
)