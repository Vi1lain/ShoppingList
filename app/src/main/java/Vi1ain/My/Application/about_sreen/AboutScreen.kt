package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.ui.theme.BlueLight
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun AboutScreen() {
    val ureHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.villain),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "This app developed by Villain \n  " +
                    "Version 1.0.0 \n" +
                    "To get more information \n",
            textAlign = TextAlign.Center
        )
        Text(text = ">>> click me <<<", modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                ureHandler.openUri("https://devildark.com")
            }, color = BlueLight
        )
    }
}