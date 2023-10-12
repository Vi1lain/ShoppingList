package Vi1ain.My.Application.navigation

import Vi1ain.My.Application.shopping_list_sreen.AboutScreen
import Vi1ain.My.Application.shopping_list_sreen.NoteScreen
import Vi1ain.My.Application.shopping_list_sreen.SettingsScreen
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListScreen
import Vi1ain.My.Application.utils.Routes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SHOPPING_LIST ){
        composable(Routes.SHOPPING_LIST){ ShoppingListScreen()}
        composable(Routes.NOTE_LIST){ NoteScreen() }
        composable(Routes.SETTINGS){ SettingsScreen() }
        composable(Routes.ABOUT){ AboutScreen() }
    }
}