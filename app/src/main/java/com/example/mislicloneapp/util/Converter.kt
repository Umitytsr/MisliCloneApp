package com.example.mislicloneapp.util

import androidx.room.TypeConverter
import com.example.mislicloneapp.data.model.At
import com.example.mislicloneapp.data.model.Br
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.model.Ht
import com.example.mislicloneapp.data.model.Pe
import com.example.mislicloneapp.data.model.Sc
import com.example.mislicloneapp.data.model.To
import com.example.mislicloneapp.data.model.İmg
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    var gson = Gson()

    @TypeConverter
    fun atToJson(value: At?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToAt(value: String): At = gson.fromJson(value, At::class.java)

    @TypeConverter
    fun brToJson(value: Br?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToBr(value: String): Br = gson.fromJson(value, Br::class.java)

    @TypeConverter
    fun htToJson(value: Ht?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToHt(value: String): Ht = gson.fromJson(value, Ht::class.java)

    @TypeConverter
    fun imgToJson(value: İmg?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToImg(value: String): İmg = gson.fromJson(value, İmg::class.java)

    @TypeConverter
    fun peToJson(value: Pe?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToPe(value: String): Pe = gson.fromJson(value, Pe::class.java)

    @TypeConverter
    fun scToJson(value: Sc?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToSc(value: String): Sc = gson.fromJson(value, Sc::class.java)

    @TypeConverter
    fun toToJson(value: To?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToTo(value: String): To = gson.fromJson(value, To::class.java)

    // List<Data> for MatchResponse
    @TypeConverter
    fun dataListToJson(value: List<Data>?): String = gson.toJson(value)

    @TypeConverter
    fun jsonToDataList(value: String): List<Data> {
        val type = object : TypeToken<List<Data>>() {}.type
        return gson.fromJson(value, type)
    }
}