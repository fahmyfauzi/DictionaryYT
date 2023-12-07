package com.plcoding.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo


/**
 * Fungsi [WordInfoItem] adalah komponen Compose untuk menampilkan informasi kata dalam antarmuka pengguna.
 *
 * @param wordInfo Objek [WordInfo] yang berisi informasi kata yang akan ditampilkan.
 * @param modifier [Modifier] untuk mengonfigurasi tata letak dan penataan visual.
 */
@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Menampilkan kata dengan ukuran dan gaya teks khusus
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        // Menampilkan fonetik kata
        Text(text = wordInfo.phonetic, fontWeight = FontWeight.Light)
        // Menambahkan ruang kosong vertikal
        Spacer(modifier = Modifier.height(16.dp))
        // Menampilkan asal kata
        Text(text = wordInfo.origin)
        // Iterasi melalui makna kata
        wordInfo.meanings.forEach { meaning ->
            // Menampilkan jenis kata dengan teks tebal
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            // Iterasi melalui definisi kata
            meaning.definitions.forEachIndexed { i, definition ->
                // Menampilkan nomor definisi dan teks definisi
                Text(text = "${i + 1}. ${definition.definition}")

                // Menambahkan ruang kosong vertikal
                Spacer(modifier = Modifier.height(8.dp))

                // Menampilkan contoh penggunaan (jika ada)
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
                // Menambahkan ruang kosong vertikal
                Spacer(modifier = Modifier.height(8.dp))
            }
            // Menambahkan ruang kosong vertikal setelah setiap makna kata
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}