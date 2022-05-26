package com.example.timepie.models

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

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