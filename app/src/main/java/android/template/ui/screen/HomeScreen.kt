package com.theolin.filemanagerv2.ui.theme.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import android.template.ui.state.HomeScreenUiState
import android.template.ui.viewmodels.HomeViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    when(state.value){
        is HomeScreenUiState.Loading -> {
            CircularProgressIndicator()
        }
        is HomeScreenUiState.Success -> {
           Column {
               (state.value as HomeScreenUiState.Success).files.forEach{
                   Text(it.name)
               }
               Spacer(modifier = Modifier.size(12.dp))
           }
        }

        is HomeScreenUiState.Error -> {
            //TODO : Make Error Screen
            Text(text = "Error")
        }
        HomeScreenUiState.Idle -> {

        }
    }

}