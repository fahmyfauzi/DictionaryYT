package com.plcoding.dictionary.feature_dictionary.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Kelas [GsonParser] adalah implementasi dari [JsonParser] yang menggunakan
 * library Gson untuk melakukan parsing JSON.
 *
 * @property gson Objek Gson yang digunakan untuk parsing JSON.
 */
class GsonParser(
    private val gson:Gson
) : JsonParser {
    /**
     * Fungsi [fromJson] digunakan untuk mengonversi JSON menjadi objek dengan tipe [T].
     *
     * @param json String JSON yang akan di-parse.
     * @param type Tipe objek yang dihasilkan setelah parsing.
     * @return Objek hasil parsing atau null jika parsing gagal.
     */
    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json,type)
    }

    /**
     * Fungsi [toJson] digunakan untuk mengonversi objek [obj] menjadi representasi JSON.
     *
     * @param obj Objek yang akan diubah menjadi JSON.
     * @param type Tipe objek yang diubah menjadi JSON.
     * @return String JSON hasil konversi atau null jika konversi gagal.
     */
    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj,type)
    }
}