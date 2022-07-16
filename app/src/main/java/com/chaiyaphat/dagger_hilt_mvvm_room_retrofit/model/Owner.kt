package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Owner(val avatar_url: String?)

class TypeConverterOwner{
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Owner? {
        if (data == null) return null
        val listType: Type = object : TypeToken<Owner?>() {}.type
        return gson.fromJson<Owner?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObject: Owner?): String? {
        return gson.toJson(someObject)
    }
}