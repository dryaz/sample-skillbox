package com.dimlix.skillboxsample.users.data

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("data")
    val data: List<UserData>
)