package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.ui.theme.DarkText
import Vi1ain.My.Application.ui.theme.LightGreenBackground
import Vi1ain.My.Application.ui.theme.LightText
import Vi1ain.My.Application.ui.theme.Purple
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun UiShoppingListItem(item: ShoppingListItem, onEvent: (ShoppingListEvent) -> Unit) {
    ConstraintLayout(modifier = Modifier.padding(start = 3.dp, end = 3.dp, top = 18.dp)) {
        val (card, editBottom, deleteBottom, counter) = createRefs()
        Card(modifier = Modifier
            .clickable {


            }

            .fillMaxWidth()
            .constrainAs(card) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(color = DarkText),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = item.time,
                    style = TextStyle(color = LightText),
                    fontSize = 12.sp
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    trackColor = Color.Green, progress = 0.5f
                )
            }
        }
        IconButton(modifier = Modifier
            .constrainAs(deleteBottom) {
                top.linkTo(card.top)
                end.linkTo(card.end)
                bottom.linkTo(card.top)

            }
            .padding(end = 15.dp)
            .size(30.dp), onClick = { onEvent(ShoppingListEvent.OnShowDeleteDialog(item)) }) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Red)
                    .padding(5.dp),
                tint = Color.White,
            )

        }
        IconButton(modifier = Modifier
            .constrainAs(editBottom) {
                top.linkTo(card.top)
                end.linkTo(deleteBottom.start)
                bottom.linkTo(card.top)

            }
            .padding(end = 5.dp)
            .size(30.dp), onClick = { onEvent(ShoppingListEvent.OnShowEditDialog(item)) }) {
            Icon(
                painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "Edit",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = LightGreenBackground)
                    .padding(5.dp),
                tint = Color.Black,
            )

        }
        Card(

            shape = RoundedCornerShape(5.dp),
            modifier = Modifier

                .constrainAs(counter) {
                    top.linkTo(card.top)
                    end.linkTo(editBottom.start)
                    bottom.linkTo(card.top)

                }
                .padding(end = 5.dp)
        ) {
            Text(
                text = "${item.allItemsCount}/${item.allSelectedItemsCount}", color = Color.White,
                modifier = Modifier
                    .background(color = Purple)
                    .padding(
                        top = 3.dp,
                        bottom = 3.dp,
                        start = 5.dp,
                        end = 5.dp
                    ),
            )
        }
    }
}