package com.appinsnap.lwmc.data.api

import com.appinsnap.lwmc.dataclasses.model.request.LoginDataModel
import com.appinsnap.lwmc.dataclasses.model.response.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("MobileAPI/Login")
    suspend fun callLoginApi(@Body loginDataModel: LoginDataModel): Response<LoginResponseModel>

}