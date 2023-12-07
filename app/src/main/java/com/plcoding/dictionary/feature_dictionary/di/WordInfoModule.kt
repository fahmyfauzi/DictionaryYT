package com.plcoding.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.plcoding.dictionary.feature_dictionary.data.local.Converters
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDao
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.plcoding.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.plcoding.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.plcoding.dictionary.feature_dictionary.data.util.GsonParser
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.plcoding.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Modul Dagger Hilt [WordInfoModule] menyediakan dependency untuk fitur kamus.
 */
@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    /**
     * Fungsi [provideGetWordInfoUseCase] menyediakan instance [GetWordInfo] yang
     * membutuhkan [WordInfoRepository].
     *
     * @param repository Repository yang digunakan oleh [GetWordInfo].
     * @return Instance dari [GetWordInfo].
     */
    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository:WordInfoRepository):GetWordInfo{
        return GetWordInfo(repository)
    }

    /**
     * Fungsi [provideWordInfoRepository] menyediakan instance [WordInfoRepository]
     * yang membutuhkan [WordInfoDatabase] dan [DictionaryApi].
     *
     * @param db Database lokal untuk menyimpan data kata.
     * @param api Objek [DictionaryApi] untuk mengakses data dari jarak jauh.
     * @return Instance dari [WordInfoRepository].
     */
    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db:WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository{
        return  WordInfoRepositoryImpl(api,db.dao)
    }

    /**
     * Fungsi [provideWordInfoDatabase] menyediakan instance [WordInfoDatabase] yang
     * membutuhkan [Application].
     *
     * @param app Aplikasi Android.
     * @return Instance dari [WordInfoDatabase].
     */
    @Provides
    @Singleton
    fun provideWordInfoDatabase(app:Application) : WordInfoDatabase{
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    /**
     * Fungsi [provideDictionaryApi] menyediakan instance [DictionaryApi] yang
     * dibangun menggunakan Retrofit.
     *
     * @return Instance dari [DictionaryApi].
     */
    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}