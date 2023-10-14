package Vi1ain.My.Application.add_item_sreen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.data.AddItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun UiAddItem(
    item: AddItem,
    onEvent: (AddItemEvent) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 3.dp)
        .clickable {
onEvent(AddItemEvent.OnShowEditDialog(item))
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                text = item.name,
                fontSize = 12.sp
            )
            Checkbox(checked = item.isCheck, onCheckedChange = {isChecked->
onEvent(AddItemEvent.OncheckedChange(item.copy(isCheck=isChecked)))
            })
            IconButton(onClick = {
                onEvent(AddItemEvent.OnDelete(item))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = "Delete"
                )
            }
        }
    }
}