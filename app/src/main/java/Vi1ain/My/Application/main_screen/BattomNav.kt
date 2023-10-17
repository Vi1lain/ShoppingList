package Vi1ain.My.Application.main_screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(currentRoute:String?,OnNavigate:(String)->Unit) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.SettingItem,
        BottomNavItem.AboutItem,
    )
    NavigationBar() {
        listItems.forEach { bottomNavItem ->

            NavigationBarItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = { OnNavigate(bottomNavItem.route)},
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavItem.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = bottomNavItem.title)
                }, alwaysShowLabel = false, colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray, selectedIconColor  = Color.Blue,
                )
            )
        }
    }
}