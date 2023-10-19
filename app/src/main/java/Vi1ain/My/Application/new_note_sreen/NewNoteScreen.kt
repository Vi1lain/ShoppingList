package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.ui.theme.BlueLight
import Vi1ain.My.Application.ui.theme.DarkText
import Vi1ain.My.Application.ui.theme.GrayLight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NewNoteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLight)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Title", fontSize = 14.sp) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = BlueLight
                        ),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "Save", tint = BlueLight
                        )
                    }
                }
                TextField(modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = {}, label = {
                        Text(
                            text = "Description",
                            fontSize = 14.sp
                        )
                    }, colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        ), textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = DarkText
                    )
                )
            }
        }
    }
}