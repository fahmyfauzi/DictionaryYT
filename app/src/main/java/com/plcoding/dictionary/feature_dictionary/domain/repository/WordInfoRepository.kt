package com.plcoding.dictionary.feature_dictionary.domain.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


/**
 * Antarmuka [WordInfoRepository] adalah kontrak untuk repository yang bertanggung
 * jawab mendapatkan informasi kata dari sumber data lokal dan jarak jauh (remote).
 */
interface WordInfoRepository {

    /**
     * Fungsi [getWordInfo] mengembalikan [Flow] dari [Resource] yang berisi informasi
     * kata berdasarkan kata kunci [word]. Informasi dapat berasal dari sumber data lokal
     * atau sumber data jarak jauh, tergantung pada ketersediaan dan validitas data.
     *
     * @param word Kata kunci untuk mencari informasi kata.
     * @return [Flow] dari [Resource] yang berisi informasi kata.
     */
    fun getWordInfo(word:String): Flow<Resource<List<WordInfo>>>
}