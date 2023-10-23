package Vi1ain.My.Application.main_screen


import Vi1ain.My.Application.R
import Vi1ain.My.Application.dialog.MainDialog
import Vi1ain.My.Application.navigation.NavigationGraph
import Vi1ain.My.Application.utils.Routes
import Vi1ain.My.Application.utils.UiEvent
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.NavigateMain -> {
                    mainNavHostController.navigate(uiEvent.route)
                }

                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }

                else -> {}
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomNav(currentRoute) { route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },
        floatingActionButton = {
            if (viewModel.showFloatingButtom.value)
                FloatingActionButton(onClick = {
                    viewModel.onEvent(event = MainScreenEvent.OnNewItemClick(currentRoute?:Routes.SHOPPING_LIST))
                }) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Add"
                    )
                }
        }, floatingActionButtonPosition = FabPosition.Center
    ) {
        NavigationGraph(navController = navController) { route ->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))
        }
        MainDialog(dialogController = viewModel)

    }
}