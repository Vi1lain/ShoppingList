package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.add_item_sreen.AddItemEvent
import Vi1ain.My.Application.add_item_sreen.AddItemViewModel
import Vi1ain.My.Application.add_item_sreen.UiAddItem
import Vi1ain.My.Application.dialog.MainDialog
import Vi1ain.My.Application.ui.theme.BlueLight
import Vi1ain.My.Application.ui.theme.DarkText
import Vi1ain.My.Application.ui.theme.GrayLight
import Vi1ain.My.Application.ui.theme.LightGreenBackground
import Vi1ain.My.Application.utils.UiEvent
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val itemsList = viewModel.itemList?.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        uiEvent.message
                    )
                }

                else -> {}
            }
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = { text ->
                            viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = { Text(text = "New Item", fontSize = 12.sp) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(fontSize = 16.sp, color = DarkText),
                        singleLine = true
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(AddItemEvent.OnItemSave)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_icon),
                            contentDescription = "add"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp, start = 3.dp, end = 3.dp)
            ) {
                if (itemsList != null) {
                    items(itemsList.value) { item ->
                        UiAddItem(item = item, onEvent = { event ->
                            viewModel.onEvent(event)
                        })

                    }
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

}