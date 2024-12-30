package com.example.aplikasisiswa.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasisiswa.cmwidget.CostumeTopAppBar
import com.example.aplikasisiswa.navigasi.DestinasiNavigasi
import com.example.aplikasisiswa.viewmodel.PenyediaViewModel
import com.example.aplikasisiswa.viewmodel.UpdateViewModel
import com.example.aplikasisiswa.viewmodel.toMhs
import kotlinx.coroutines.launch

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
    override val titleRes = "Update Mhs"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // Collect the UI state from the ViewModel
    val uiState = viewModel.uiState.value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(17.dp)
        ) {
            // Pass the UI state to the EntryBody
            EntryBody(
                insertUiState = uiState,
                onSiswaValueChange = { updatedValue ->
                    viewModel.updateMhsState(updatedValue) // Update ViewModel state
                },
                onSaveClick = {
                    uiState.insertUiEvent?.let { insertUiEvent ->
                        coroutineScope.launch {
                            // Call ViewModel update method
                            viewModel.updateMhs(
                                nim = viewModel.nim, // Pass the NIM from ViewModel
                                mahasiswa = insertUiEvent.toMhs() // Convert InsertUiEvent to Mahasiswa
                            )
                            navigateBack() // Navigate back after saving
                        }
                    }
                }
            )
        }
    }
}