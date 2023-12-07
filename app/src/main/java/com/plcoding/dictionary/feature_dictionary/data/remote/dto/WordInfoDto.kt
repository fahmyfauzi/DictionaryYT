package com.plcoding.dictionary.feature_dictionary.data.remote.dto

import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoEntity
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

/**
 * Kelas data [WordInfoDto] merepresentasikan struktur data yang diterima dari API
 * terkait informasi suatu kata dalam bahasa Inggris.
 */

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin:String?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    /**
     * Fungsi [toWordInfoEntity] mengonversi objek [WordInfoDto] menjadi objek
     * domain [WordInfoEntity].
     *
     * @return Objek [WordInfoEntity] yang dibentuk dari data dalam objek [WordInfoDto].
     */
    fun toWordInfoEntity(): WordInfoEntity{
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            word = word
        )
    }
}