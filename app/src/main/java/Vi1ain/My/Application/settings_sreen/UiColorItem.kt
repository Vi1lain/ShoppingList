package Vi1ain.My.Application.settings_sreen


import Vi1ain.My.Application.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun UiColorItem(
    item: ColorItem,
    onEvent: (SettingsEvent) -> Unit
) {
    IconButton(
        onClick = { onEvent(SettingsEvent.OnItemSelected(item.color)) }, modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(CircleShape)
            .size(35.dp)
            .background(color = Color(android.graphics.Color.parseColor(item.color)))
    ) {
        if (item.isSelected) Icon(
            painter = painterResource(id = R.drawable.check),
            contentDescription = "check",
            tint = Color.White
        )
    }
}