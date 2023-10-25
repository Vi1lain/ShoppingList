package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.dialog.MainDialog
import Vi1ain.My.Application.note_list_sreen.NoteListEvent
import Vi1ain.My.Application.note_list_sreen.NoteListViewModel
import Vi1ain.My.Application.note_list_sreen.UiNoteItem
import Vi1ain.My.Application.ui.theme.GrayLight
import Vi1ain.My.Application.ui.theme.LightGreenBackground
import Vi1ain.My.Application.utils.UiEvent
import android.annotation.SuppressLint
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val itemsList = viewModel.noteList.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                is UiEvent.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = "Undone"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.OnEvent(NoteListEvent.OnDoneDeleteItem)
                    }
                }

                else -> {}
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color.Red,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight), contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(itemsList.value) { item ->
                UiNoteItem(item) { event -> viewModel.OnEvent(event) }

            }
        }
    }
    MainDialog(dialogController = viewModel)
    if (itemsList?.value?.isEmpty() == true) {
        Text(
            color = LightGreenBackground,
            text = "Empty",
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}