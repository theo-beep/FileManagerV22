package com.theolin.filemanagerv2.ui.theme.screen

import android.os.Build
import android.template.ui.components.FileCardComponent
import android.template.ui.state.HomeScreenUiState
import android.template.ui.viewmodels.HomeViewModel
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    when (state.value) {
        is HomeScreenUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeScreenUiState.Success -> {
            LazyColumn (
                modifier = Modifier.padding(12.dp) ,
                contentPadding  = PaddingValues(4.dp),
            ){
                items((state.value as HomeScreenUiState.Success).files) {
                    Spacer(modifier = Modifier.size(6.dp))
                    FileCardComponent(it.name, it.type)
                }
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