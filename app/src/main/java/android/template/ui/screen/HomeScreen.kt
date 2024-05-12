package android.template.ui.screen

import android.os.Build
import android.template.ui.components.ErrorDialog
import android.template.ui.components.FileCardComponent
import android.template.ui.state.HomeScreenUiState
import android.template.ui.viewmodels.HomeViewModel
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("File Explorer")
                }
            )
        },
        bottomBar = {
            //TODO : Disable but maybe add in later
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
        },
        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.addNewFile("")
                })
            {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add New File Path")
                Text(text = "Add File")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (state.value) {
                is HomeScreenUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeScreenUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(12.dp),
                        contentPadding = PaddingValues(4.dp),
                    ) {
                        items((state.value as HomeScreenUiState.Success).files) {
                            Spacer(modifier = Modifier.size(6.dp))
                            //TODO : add impl
                            FileCardComponent(
                                title = it.name,
                                text = it.type,
                                onEditClick = {},
                                onDeleteClick = {})
                        }
                    }
                }

                is HomeScreenUiState.Error -> {
                    //TODO : Make Error Screen
                    ErrorDialog(
                        (state.value as HomeScreenUiState.Error).error.orEmpty(),
                        onDismiss = {

                        }
                    )
                }

                HomeScreenUiState.Idle -> {
                }
            }
        }
    }
}

