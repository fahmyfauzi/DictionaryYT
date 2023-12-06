package com.plcoding.dictionary.feature_dictionary.data.util

import java.lang.reflect.Type

/**
 * Antarmuka [JsonParser] adalah kontrak untuk implementasi parser JSON yang dapat
 * digunakan dalam berbagai konteks.
 */
interface JsonParser {
    /**
     * Fungsi [fromJson] digunakan untuk mengonversi JSON menjadi objek dengan tipe [T].
     *
     * @param json String JSON yang akan di-parse.
     * @param type Tipe objek yang dihasilkan setelah parsing.
     * @return Objek hasil parsing atau null jika parsing gagal.
     */
    fun <T> fromJson(json:String,type: Type): T?



    /**
     * Fungsi [toJson] digunakan untuk mengonversi objek [obj] menjadi representasi JSON.
     *
     * @param obj Objek yang akan diubah menjadi JSON.
     * @param type Tipe objek yang diubah menjadi JSON.
     * @return String JSON hasil konversi atau null jika konversi gagal.
     */
    fun <T> toJson(obj:T,type: Type):String?
}