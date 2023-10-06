package Vi1ain.My.Application.main_screen


import Vi1ain.My.Application.R
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(bottomBar = {}, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
Icon(painter = painterResource(id = R.drawable.add_icon), contentDescription = "add")
        }
    }, floatingActionButtonPosition = FabPosition.Center) {

    }
}