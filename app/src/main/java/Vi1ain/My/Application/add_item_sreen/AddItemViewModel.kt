package Vi1ain.My.Application.add_item_sreen

import Vi1ain.My.Application.data.AddItem
import Vi1ain.My.Application.data.AddItemRepository
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), DialogController {
    var itemList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemList = listId.let { repository.getAllItemsBid(it) }
    }

    var itemText = mutableStateOf("")
        private set
    override var dialogTitle = mutableStateOf("Edit name: ")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    repository.insertItem(
                        AddItem(
                            addItem?.id,
                            itemText.value,
                            addItem?.isCheck ?: false,
                            listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
            }

            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }

            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(item = event.item)
                }
            }
            is AddItemEvent.OncheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
            }
        }
    }

    override fun OnDialogEvent(event: DialgoEvent) {
        when (event) {
            is DialgoEvent.OnCansel -> {
                openDialog.value = false
                editableText.value = ""
            }

            is DialgoEvent.OnConfirm -> {
                openDialog.value = false
                itemText.value = editableText.value
                editableText.value = ""
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }

}