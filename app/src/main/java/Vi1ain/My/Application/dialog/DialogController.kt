package Vi1ain.My.Application.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
    val dialogTitle:MutableState<String>
    val editableText:MutableState<String>
    val openDialog:MutableState<Boolean>
    val showEditableText:MutableState<Boolean>
    fun OnDialogEvent(event: DialgoEvent)


}