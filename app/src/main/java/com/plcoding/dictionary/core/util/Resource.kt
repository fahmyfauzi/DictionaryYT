package com.plcoding.dictionary.core.util

/**
 * Alias tipe [SimpleResource] digunakan untuk menyederhanakan penggunaan [Resource]
 * dalam kasus di mana data hasil operasi tidak relevan atau tidak diperlukan.
 */
typealias SimpleResource = Resource<Unit>

/**
 * Sealed class [Resource] adalah wrapper yang digunakan untuk mengelola status
 * sukses, loading, dan error pada operasi yang melibatkan sumber daya tertentu.
 *
 * @property data Data hasil operasi, bisa null tergantung pada jenis [Resource].
 * @property message Pesan informasi yang menyertai [Resource], bisa null.
 */


sealed class Resource<T> (val data:T?=null,val message:String?=null){
    /**
     * Subkelas [Loading] dari [Resource] digunakan untuk menunjukkan bahwa sedang
     * dalam tahap loading atau proses operasi sedang berlangsung.
     *
     * @param data Data hasil operasi, bisa null.
     */
    class Loading<T>(data: T?=null):Resource<T>(data)

    /**
     * Subkelas [Success] dari [Resource] digunakan untuk menunjukkan bahwa operasi
     * telah berhasil dengan data hasil operasi.
     *
     * @param data Data hasil operasi, bisa null.
     */
    class Success<T>(data: T?) :Resource<T>(data)

    /**
     * Subkelas [Error] dari [Resource] digunakan untuk menunjukkan bahwa terjadi
     * kesalahan dalam operasi dengan pesan informasi yang menyertainya.
     *
     * @param message Pesan informasi yang menyertai kesalahan.
     * @param data Data hasil operasi, bisa null.
     */
    class Error<T>(message: String,data: T?=null):Resource<T>(data, message)
}