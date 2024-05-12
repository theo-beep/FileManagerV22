package android.template.ui.viewmodels

import android.template.common.DataResource
import android.template.domain.usecases.AddNewFileUsecase
import android.template.domain.usecases.GetAllFilesUsecase
import android.template.domain.usecases.RefreshAllFilesUseCase
import android.template.ui.state.HomeScreenUiState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val files: GetAllFilesUsecase,
    private val refreshFiles: RefreshAllFilesUseCase,
    private val addNewFile: AddNewFileUsecase
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState.Idle)
    val state: StateFlow<HomeScreenUiState> = _state

    init {
        refreshFiles()
    }

    private fun refreshFiles() {
        viewModelScope.launch {
            _state.value = HomeScreenUiState.Loading
            refreshFiles.invoke().collect {
                when (it) {
                    is DataResource.Success -> {
                        _state.value = HomeScreenUiState.Success(it.data ?: emptyList())
                    }

                    is DataResource.Error -> {
                        _state.value = HomeScreenUiState.Error(it.message)
                        files.invoke().collect {
                            _state.value = HomeScreenUiState.Success(it.data ?: emptyList())
                        }

                    }

                    is DataResource.Loading -> {
                        _state.value = HomeScreenUiState.Loading

                    }
                }
            }
        }
    }

    fun addNewFile(path: String) {
        viewModelScope.launch {
            addNewFile.invoke(path)
        }
    }
}