package com.plcoding.dictionary.feature_dictionary.data.local

import androidx.room.Database

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
abstract class WordInfoDatabase {
    abstract val dao:WordInfoDao
}