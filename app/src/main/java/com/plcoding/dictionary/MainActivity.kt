package com.plcoding.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoItem
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoViewModel
import com.plcoding.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
                // Mendapatkan instance ViewModel menggunakan Hilt
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                // Menggunakan LaunchedEffect untuk mengumpulkan event dari ViewModel
                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when(event) {
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }
                // Menggunakan Scaffold sebagai struktur layout utama
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    // Menggunakan Box untuk mengatur tata letak dan latar belakang
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        // Column untuk menyusun elemen UI secara vertikal
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // TextField untuk input pencarian kata
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            // Spacer untuk memberikan ruang vertikal
                            Spacer(modifier = Modifier.height(16.dp))
                            // LazyColumn untuk menampilkan daftar kata
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(state.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if(i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    // Memanggil komponen WordInfoItem untuk setiap kata
                                    WordInfoItem(wordInfo = wordInfo)
                                    if(i < state.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                        // Menampilkan indikator loading jika sedang memuat data
                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}