package Vi1ain.My.Application.utils

import androidx.compose.runtime.MutableState

interface DialogController {
    val dialogTitle:MutableState<String>
    val editableText:MutableState<String>
    val openDialog:MutableState<Boolean>
    val showEditableText:MutableState<Boolean>

}