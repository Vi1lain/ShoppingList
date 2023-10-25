package Vi1ain.My.Application.note_list_sreen

import Vi1ain.My.Application.data.NoteItem
import Vi1ain.My.Application.ui.theme.BlueLight
import Vi1ain.My.Application.ui.theme.LightText
import Vi1ain.My.Application.utils.Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun UiNoteItem(
    item: NoteItem,
    onEvent: (NoteListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 3.dp, top = 3.dp, end = 3.dp
            )
            .clickable {
                onEvent(
                    NoteListEvent.OnItemClick(
                        Routes.NEW_NOTE + "/${item.id}"
                    )
                )
            }
    ) {
        Column(Modifier.fillMaxWidth().background(Color.White)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f),
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.time,
                    modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                    color = BlueLight,
                    fontSize = 12.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 3.dp, start = 10.dp, bottom = 7.dp)
                        .weight(1f),
                    text = item.description,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = LightText
                    //fontWeight = FontWeight.Bold
                )
                IconButton(onClick = {
                    onEvent(NoteListEvent.OnShowDeleteDialog(item))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}