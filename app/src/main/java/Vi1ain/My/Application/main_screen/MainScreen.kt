package Vi1ain.My.Application.main_screen


import Vi1ain.My.Application.R
import Vi1ain.My.Application.navigation.NavigationGraph
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNav(navController = navController)}, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
Icon(tint = Color.White, painter = painterResource(id = R.drawable.add_icon), contentDescription = "Add")
        }
    }, floatingActionButtonPosition = FabPosition.Center) {
        NavigationGraph(navController = navController)

    }
}