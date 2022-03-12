package id.elfastudio.moviescatalogue.core.utils

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class GenreConverter {

    @TypeConverter
    fun fromGenre(value: String): List<String>{
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter = moshi.adapter<List<String>>(type)
        return jsonAdapter.fromJson(value) ?: arrayListOf()
    }

    @TypeConverter
    fun toGenre(value: List<String>?): String{
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter = moshi.adapter<List<String>>(type)
        return jsonAdapter.toJson(value) ?: ""
    }

}