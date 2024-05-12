package android.template.ui.state

import android.template.domain.models.FileDomain


sealed class HomeScreenUiState {
    data object Idle : HomeScreenUiState()
    data object Loading : HomeScreenUiState()
    data class Success(val files: List<FileDomain>) : HomeScreenUiState()
    data class Error(val error: String?) : HomeScreenUiState()
}