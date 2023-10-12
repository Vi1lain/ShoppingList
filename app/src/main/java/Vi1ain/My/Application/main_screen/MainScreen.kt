package Vi1ain.My.Application.main_screen


import Vi1ain.My.Application.R
import Vi1ain.My.Application.dialog.MainDialog
import Vi1ain.My.Application.navigation.NavigationGraph
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListViewModel
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNav(navController = navController) }, floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.onEvent(event = MainScreenEvent.OnShowEditDialog)
        }) {
            Icon(
                tint = Color.White,
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = "Add"
            )
        }
    }, floatingActionButtonPosition = FabPosition.Center) {
        NavigationGraph(navController = navController)
MainDialog(dialogController = viewModel)

    }
}