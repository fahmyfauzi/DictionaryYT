package com.plcoding.dictionary.feature_dictionary.data.remote.dto

import com.plcoding.dictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
){
    //mapper
    fun toMeaning():Meaning{
        return Meaning(
            definitions = definitions.map { it.toDefinition()    },
            partOfSpeech = partOfSpeech
        )
    }
}