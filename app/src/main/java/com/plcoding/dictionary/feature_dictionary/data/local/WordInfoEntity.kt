package com.plcoding.dictionary.feature_dictionary.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.dictionary.feature_dictionary.domain.model.Meaning
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word:String,
    val phonetic:String?,
    val origin:String?,
    val meanings:List<Meaning>,
    @PrimaryKey val id:Int? = null
){
    //mapper
    fun toWordInfo():WordInfo{
        return WordInfo(
            meanings= meanings,
            word =  word,
            origin = origin ?: "",
            phonetic = phonetic?:""
        )
    }
}
