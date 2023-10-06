package Vi1ain.My.Application.main_screen


import Vi1ain.My.Application.R
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(bottomBar = {BottomNav()}, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
Icon(tint = Color.White, painter = painterResource(id = R.drawable.add_icon), contentDescription = "Add")
        }
    }, floatingActionButtonPosition = FabPosition.Center) {

    }
}