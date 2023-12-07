package com.plcoding.dictionary.feature_dictionary.presentation

import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

/**
 * Data class [WordInfoState] merepresentasikan state untuk tampilan kata-kata dalam aplikasi kamus.
 *
 * @property wordInfoItems List dari [WordInfo] yang berisi informasi kata.
 * @property isLoading Boolean yang menunjukkan apakah sedang dalam proses loading atau tidak.
 */
data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading:Boolean = false
)
