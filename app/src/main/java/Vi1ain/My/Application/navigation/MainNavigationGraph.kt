package Vi1ain.My.Application.navigation

import Vi1ain.My.Application.main_screen.MainScreen
import Vi1ain.My.Application.shopping_list_sreen.AddItemScreen
import Vi1ain.My.Application.shopping_list_sreen.NewNoteScreen
import Vi1ain.My.Application.utils.Routes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.ADD_ITEM + "/{listId}") { AddItemScreen() }
        composable(Routes.NEW_NOTE) { NewNoteScreen() { navController.popBackStack() } }
        composable(Routes.MAIN_SCREEN) { MainScreen(navController) }
    }
}