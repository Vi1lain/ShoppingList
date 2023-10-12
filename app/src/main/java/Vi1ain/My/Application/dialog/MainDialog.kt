package Vi1ain.My.Application.dialog

import Vi1ain.My.Application.ui.theme.DarkText
import Vi1ain.My.Application.ui.theme.GrayLight
import Vi1ain.My.Application.ui.theme.LightText

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDialog(dialogController: DialogController) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = { dialogController.OnDialogEvent(event = DialgoEvent.OnCansel) },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = dialogController.dialogTitle.value,
                        style = TextStyle(
                            color = DarkText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (dialogController.showEditableText.value)
                        TextField(
                            value = dialogController.editableText.value,
                            onValueChange = { text ->
                                dialogController.OnDialogEvent(
                                    event = DialgoEvent.TextChange(text)
                                )
                            },
                            label = { Text(text = "List name") },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = GrayLight,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(9.dp),
                            textStyle = TextStyle(LightText, fontSize = 16.sp)
                        )

                }
            },
            confirmButton = {
                TextButton(onClick = { dialogController.OnDialogEvent(DialgoEvent.OnConfirm) }) {
                    Text(text = "Enter")
                }
            }, dismissButton = {
                TextButton(onClick = { dialogController.OnDialogEvent(DialgoEvent.OnCansel) }) {
                    Text(text = "Cansel")
                }
            })
    }
}