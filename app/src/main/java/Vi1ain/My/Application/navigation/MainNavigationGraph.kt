package Vi1ain.My.Application.navigation

import Vi1ain.My.Application.main_screen.MainScreen
import Vi1ain.My.Application.shopping_list_sreen.AboutScreen
import Vi1ain.My.Application.shopping_list_sreen.AddItemScreen
import Vi1ain.My.Application.shopping_list_sreen.NewNoteScreen
import Vi1ain.My.Application.shopping_list_sreen.NoteScreen
import Vi1ain.My.Application.shopping_list_sreen.SettingsScreen
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListScreen
import Vi1ain.My.Application.utils.Routes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigationGraph( ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN ){
        composable(Routes.ADD_ITEM){ AddItemScreen() }
        composable(Routes.NEW_NOTE){ NewNoteScreen() }
        composable(Routes.MAIN_SCREEN){ MainScreen(navController) }
    }
}