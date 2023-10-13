package Vi1ain.My.Application.add_item_sreen

import Vi1ain.My.Application.data.AddItem
import Vi1ain.My.Application.data.AddItemRepository
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel@Inject constructor(
    private val repository: AddItemRepository,
savedStateHandle: SavedStateHandle
    ):ViewModel(),DialogController {
var itemList: Flow<List<AddItem>>? = null
    init {
        val listId = savedStateHandle.get<String>("listId")?.toInt()
        itemList = listId?.let { repository.getAllItemsBid(it) }
    }

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

fun onEvent(){}

    override fun OnDialogEvent(event: DialgoEvent) {
        when (event) {
            is DialgoEvent.OnCansel -> {
                openDialog.value = false
                editableText.value =""
            }

            is DialgoEvent.OnConfirm -> {
                openDialog.value = false
                editableText.value =""
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }

}