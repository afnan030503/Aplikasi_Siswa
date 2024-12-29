package com.example.aplikasisiswa.viewmodel

import com.example.aplikasisiswa.model.Mahasiswa

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val nim: String ="",
    val nama: String ="",
    val alamat: String ="",
    val jeniskelamin: String ="",
    val kelas: String ="",
    val angkatan: String ="",
)

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)