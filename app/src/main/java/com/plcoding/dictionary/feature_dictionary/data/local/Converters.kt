package com.plcoding.dictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.plcoding.dictionary.feature_dictionary.data.util.JsonParser
import com.plcoding.dictionary.feature_dictionary.domain.model.Meaning

/**
 * Kelas [Converters] berisi konverter-konverter tipe data untuk Room Database,
 * terutama untuk tipe data [Meaning].
 *
 * @property jsonParser Objek [JsonParser] yang digunakan untuk parsing dan
 * serialisasi JSON.
 */
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    /**
     * Fungsi [fromMeaningsJson] mengonversi String JSON ke dalam List [Meaning].
     *
     * @param json String JSON yang akan di-parse.
     * @return List [Meaning] hasil konversi atau List kosong jika parsing gagal.
     */
    @TypeConverter
    fun fromMeaningsJson(json:String):List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    /**
     * Fungsi [toMeaningsJson] mengonversi List [Meaning] ke dalam String JSON.
     *
     * @param meanings List [Meaning] yang akan diubah menjadi JSON.
     * @return String JSON hasil konversi atau String JSON kosong jika konversi gagal.
     */
    @TypeConverter
    fun toMeaningsJson(meanings:List<Meaning>):String{
        return jsonParser.toJson(
            meanings,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }



}