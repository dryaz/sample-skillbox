package com.dimlix.skillboxsample

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: List<UserData>
)