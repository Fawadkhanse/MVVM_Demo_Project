package com.appinsnap.lwmc.dataclasses.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponseModel(
    @SerializedName("Data")
    var data: List<Data?>?,
    @SerializedName("Status")
    var status: Boolean?
):Serializable
data class Data(
    @SerializedName("Address")
    var address: String?,
    @SerializedName("CNIC")
    var cNIC: Long?,
    @SerializedName("CreatedBy")
    var createdBy: Int?,
    @SerializedName("CreatedDateTime")
    var createdDateTime: String?,
    @SerializedName("ID")
    var iD: Int?,
    @SerializedName("IsActive")
    var isActive: Boolean?,
    @SerializedName("IsDeleted")
    var isDeleted: Boolean?,
    @SerializedName("MobileNo")
    var mobileNo: Long?,
    @SerializedName("Name")
    var name: String?,
    @SerializedName("Password")
    var password: String?,
    @SerializedName("Picture")
    var picture: Any?,
    @SerializedName("RoleID")
    var roleID: Int?,
    @SerializedName("Username")
    var username: String?
):Serializable