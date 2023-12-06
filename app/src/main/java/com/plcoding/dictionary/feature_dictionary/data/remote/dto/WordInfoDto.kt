package com.plcoding.dictionary.feature_dictionary.data.remote.dto

import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

/**
 * Kelas data [WordInfoDto] merepresentasikan struktur data yang diterima dari API
 * terkait informasi suatu kata dalam bahasa Inggris.
 */

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin:String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    /**
     * Fungsi [toWordInfo] mengonversi objek [WordInfoDto] menjadi objek
     * domain [WordInfo].
     *
     * @return Objek [WordInfo] yang dibentuk dari data dalam objek [WordInfoDto].
     */
    fun toWordInfo(): WordInfo{
        return WordInfo(
            meanings = meanings.map { it.toMeaning() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}