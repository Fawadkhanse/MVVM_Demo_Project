package com.appinsnap.lwmc.dataclasses.model.request

import com.google.gson.annotations.SerializedName

data class LoginDataModel(

    @SerializedName("Username")
    var username: String? = "",
    @SerializedName("Password")
var password: String? = ""
)