package com.plcoding.dictionary.feature_dictionary.data.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDao
import com.plcoding.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

/**
 * Kelas [WordInfoRepositoryImpl] adalah implementasi dari [WordInfoRepository]
 * yang bertanggung jawab untuk mendapatkan informasi kata dari sumber data lokal
 * dan sumber data jarak jauh (remote).
 *
 * @property api Objek [DictionaryApi] untuk mengakses sumber data jarak jauh (remote).
 * @property dao Objek [WordInfoDao] untuk mengakses sumber data lokal.
 */
class WordInfoRepositoryImpl(
    private val api :DictionaryApi,
    private val dao: WordInfoDao
) :WordInfoRepository{

    /**
     * Fungsi [getWordInfo] mengembalikan [Flow] dari [Resource] yang berisi informasi
     * kata berdasarkan kata kunci [word]. Informasi dapat berasal dari sumber data lokal
     * atau sumber data jarak jauh, tergantung pada ketersediaan dan validitas data.
     *
     * @param word Kata kunci untuk mencari informasi kata.
     * @return [Flow] dari [Resource] yang berisi informasi kata.
     */
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        // Mengirim status Loading ke pengamat
        emit(Resource.Loading())

        // Mendapatkan informasi kata dari sumber data lokal
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            // Mendapatkan informasi kata dari sumber data jarak jauh (remote)
            val remoteWordInfos= api.getWordInfo(word)

            // Menghapus dan menyimpan informasi kata ke dalam sumber data lokal
            dao.deleteWordInfos(remoteWordInfos.map{it.word})
            dao.insertWordInfos(remoteWordInfos.map{it.toWordInfoEntity()})
        }catch (e:IOException){
            // Mengirim status Error jika terjadi IOException (masalah koneksi internet)
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection",
                data = wordInfos
            ))
        }catch (e:HttpException){
            // Mengirim status Error jika terjadi HttpException (kesalahan server)
            emit(Resource.Error(
                message = "Oops, Something went wrong!",
                data = wordInfos
            ))
        }

        // Mendapatkan informasi kata terbaru dari sumber data lokal
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }

        // Mengirim status Success ke pengamat dengan informasi kata terbaru
        emit(Resource.Success(newWordInfos))
    }
}