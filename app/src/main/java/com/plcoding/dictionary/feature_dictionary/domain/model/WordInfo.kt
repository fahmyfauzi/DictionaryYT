package com.plcoding.dictionary.feature_dictionary.domain.model

import com.plcoding.dictionary.feature_dictionary.data.remote.dto.MeaningDto
import com.plcoding.dictionary.feature_dictionary.data.remote.dto.PhoneticDto

/**
 * Kelas data [WordInfo] merepresentasikan informasi suatu kata dalam bahasa Inggris
 * di dalam domain model.
 */
data class WordInfo(
    val meanings: List<Meaning>,
    val origin:String,
    val phonetic: String,
    val word: String
)
