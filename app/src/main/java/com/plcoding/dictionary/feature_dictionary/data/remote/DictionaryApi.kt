package com.plcoding.dictionary.feature_dictionary.data.remote

import com.plcoding.dictionary.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Antarmuka [DictionaryApi] adalah representasi dari API kamus yang digunakan
 * untuk mendapatkan informasi kata-kata dalam bahasa Inggris.
 */
interface DictionaryApi {

    /**
     * Fungsi [getWordInfo] digunakan untuk mengambil informasi mengenai suatu kata
     * dari kamus dengan menggunakan metode HTTP GET.
     *
     * @param word Kata yang ingin dicari informasinya dalam bahasa Inggris.
     * @return Objek yang berisi informasi mengenai kata yang dicari.
     */
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word:String
    ): List<WordInfoDto>

    companion object{
        /**
         * Konstanta [BASE_URL] adalah URL dasar untuk mengakses API kamus.
         */
        const val BASE_URL ="https://dictionaryapi.dev/"
    }
}