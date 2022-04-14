package com.example.timepie.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
class User(
    var username: String = "",
    var password: String = "",
    var phone: String = "",
    var email: String? = null
) : Parcelable {

    companion object {
        fun fromJson(jsonObject: JSONObject) : User {
            val user = User()
            user.username = jsonObject.getString("username")
            user.password = jsonObject.getString("password")
            user.phone = jsonObject.getString("phone")
            user.email = jsonObject.getString("email")

            return user
        }
    }

}