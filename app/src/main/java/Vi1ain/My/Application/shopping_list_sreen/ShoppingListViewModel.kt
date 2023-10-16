package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.data.ShoppingListRepository
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import Vi1ain.My.Application.utils.UiEvent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository,

    ) : ViewModel(), DialogController {
     val list = repository.getAllItems()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var listItem: ShoppingListItem? = null

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            listItem?.id,
                            editableText.value,
                            "02-08-1988",
                            listItem?.allItemsCount ?: 0,
                            listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }

            is ShoppingListEvent.OnItemClick -> {
                SendUiEvent(event = UiEvent.Navigate(route = event.route))
            }

            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "List name:"
                showEditableText.value = true
            }

            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete This item?"
                showEditableText.value = false
            }

        }
    }

    override fun OnDialogEvent(event: DialgoEvent) {
        when (event) {
            is DialgoEvent.OnCansel -> {
                openDialog.value = false
            }

            is DialgoEvent.OnConfirm -> {
                if (showEditableText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch { listItem?.let { repository.deleteItem(it) } }
                }
                openDialog.value = false
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }
    private fun SendUiEvent(event: UiEvent){
        viewModelScope.launch { _uiEvent.send(event) }
    }
}