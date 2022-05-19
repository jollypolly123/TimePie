package com.example.timepie.models

import android.os.Parcelable
import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONObject

//@Parcelize
//class Supplement(
//    var name: String = "Untitled",
//    var createdAt: String = "",
//    var description: String? = "",
//    var frequency: Number = 0,
//    var user: User? = null
//) : Parcelable {
//
//    companion object {
//        fun fromJson(jsonObject: JSONObject) : Supplement {
//            val supplement = Supplement()
//            supplement.name = jsonObject.getString("name")
//            supplement.description = jsonObject.getString("description")
//            supplement.frequency = jsonObject.getInt("frequency")
//            supplement.createdAt = jsonObject.getString("created_at")
//            supplement.user = User.fromJson(jsonObject.getJSONObject("user"))
//
//            return supplement
//        }
//
//        fun fromJsonArray(jsonArray: JSONArray) : List<Supplement> {
//            val supplements = ArrayList<Supplement>()
//            for (i in 0 until jsonArray.length()) {
//                supplements.add(fromJson(jsonArray.getJSONObject(i)))
//            }
//            return supplements
//        }
//    }
//}

/**
 * name : String
 * createdAt : String
 * description : String
 * frequency : Number
 * user : User
 */
@ParseClassName("Supplement")
class Supplement : ParseObject() {

    fun getName(): String? {
        return getString(KEY_NAME)
    }

    fun setName(name: String) {
        put(KEY_NAME, name)
    }

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getFrequency(): Number? {
        return getNumber(KEY_FREQUENCY)
    }

    fun setFrequency(frequency: Number) {
        put(KEY_FREQUENCY, frequency)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    companion object {
        const val KEY_NAME = "name"
        const val KEY_DESCRIPTION = "description"
        const val KEY_FREQUENCY = "frequency"
        const val KEY_USER = "user"
    }

}