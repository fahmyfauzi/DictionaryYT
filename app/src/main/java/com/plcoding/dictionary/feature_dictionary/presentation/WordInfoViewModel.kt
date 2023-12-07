package com.plcoding.dictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Kelas [WordInfoViewModel] adalah ViewModel untuk tampilan kata-kata dalam aplikasi kamus.
 *
 * @property getWordInfo Objek [GetWordInfo] yang digunakan untuk mendapatkan informasi kata.
 */

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo:GetWordInfo
) :ViewModel(){
    // State untuk input pencarian
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    // State untuk menyimpan informasi kata
    private val _state = mutableStateOf(WordInfoState())
    val state :State<WordInfoState> = _state


    // Event flow untuk menangani event UI
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    /**
     * Fungsi [onSearch] dipanggil ketika pengguna memulai pencarian kata.
     *
     * @param query Kata kunci pencarian.
     */
    fun onSearch(query:String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            // Menggunakan delay untuk menunda permintaan pencarian setelah pengguna berhenti mengetik
            delay(500L)

            // Mendapatkan informasi kata dari use case GetWordInfo
            getWordInfo(query)
                .onEach {result->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems =  result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading ->{
                        _state.value = state.value.copy(
                            wordInfoItems =  result.data ?: emptyList(),
                            isLoading = false
                        )
                        // Mengirim event untuk menampilkan snackbar
                        _eventFlow.emit(UIEvent.ShowSnackbar(
                            result.message ?: "Unknown Error"
                        ))
                    }
                    is Resource.Error ->{
                        _state.value = state.value.copy(
                            wordInfoItems =  result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    /**
     * Sealed class [UIEvent] digunakan untuk menangani event UI dalam [WordInfoViewModel].
     */
    sealed class UIEvent{
        data class  ShowSnackbar(val message:String):UIEvent()
    }

}