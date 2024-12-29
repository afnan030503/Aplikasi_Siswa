package com.example.aplikasisiswa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasisiswa.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MahasiswaRepository
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    fun fetchDetailMahasiswa(nim: String) {
        viewModelScope.launch {
            _detailUiState.value = _detailUiState.value.copy(isLoading = true)
            try {
                val mahasiswa = repository.getMahasiswaByNim(nim)
                _detailUiState.value = _detailUiState.value.copy(
                    isLoading = false,
                    detailUiEvent = mahasiswa?.toUiEvent() ?: DetailUiEvent()
                )
            } catch (e: Exception) {
                _detailUiState.value = _detailUiState.value.copy(isLoading = false)
            }
        }
    }

    fun deleteMhs(nim: String) {
        viewModelScope.launch {
            try {
                repository.deleteMahasiswaByNim(nim)
            } catch (e: Exception) {
                // Tangani error
            }
        }
    }
}
