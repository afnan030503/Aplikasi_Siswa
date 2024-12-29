package com.example.aplikasisiswa.viewmodel

import com.example.aplikasisiswa.model.Mahasiswa

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()

}