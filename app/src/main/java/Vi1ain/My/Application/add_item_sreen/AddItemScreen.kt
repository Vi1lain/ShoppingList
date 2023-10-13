package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.add_item_sreen.AddItemViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    Text(modifier = Modifier.fillMaxSize(), text = "Add Item Screen")
}