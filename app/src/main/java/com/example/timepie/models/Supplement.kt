package com.example.timepie.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
class Supplement(
    var name: String = "Untitled",
    var createdAt: String = "",
    var description: String? = "",
    var frequency: Number = 0,
    var user: User? = null
) : Parcelable {

    companion object {
        fun fromJson(jsonObject: JSONObject) : Supplement {
            val supplement = Supplement()
            supplement.name = jsonObject.getString("name")
            supplement.description = jsonObject.getString("description")
            supplement.frequency = jsonObject.getInt("frequency")
            supplement.createdAt = jsonObject.getString("created_at")
            supplement.user = User.fromJson(jsonObject.getJSONObject("user"))

            return supplement
        }

        fun fromJsonArray(jsonArray: JSONArray) : List<Supplement> {
            val supplements = ArrayList<Supplement>()
            for (i in 0 until jsonArray.length()) {
                supplements.add(fromJson(jsonArray.getJSONObject(i)))
            }
            return supplements
        }
    }
}