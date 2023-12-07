package com.plcoding.dictionary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Kelas [DictionaryApp] adalah kelas utama aplikasi yang diannotasi dengan
 * [HiltAndroidApp] untuk integrasi dengan Dagger Hilt.
 */
@HiltAndroidApp
class DictionaryApp:Application()