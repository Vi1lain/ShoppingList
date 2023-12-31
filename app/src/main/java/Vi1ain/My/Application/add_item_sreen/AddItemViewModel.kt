package Vi1ain.My.Application.add_item_sreen

import Vi1ain.My.Application.data.AddItem
import Vi1ain.My.Application.data.AddItemRepository
import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import Vi1ain.My.Application.utils.UiEvent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var itemList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var shoppingListItem: ShoppingListItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemList = repository.getAllItemsBid(listId)
        viewModelScope.launch {
            shoppingListItem = repository.getListItemsBid(listId)
        }
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
                    if (addItem!=null){
                        if (addItem!!.name.isEmpty()){
                            SendUiEvent(UiEvent.ShowSnackBar("Name is Empty!"))
                            return@launch
                        }
                    }else{
                        if (itemText.value.isEmpty()){
                            SendUiEvent(UiEvent.ShowSnackBar("Name is Empty!"))
                            return@launch
                        }
                    }
                    repository.insertItem(
                        AddItem(
                            addItem?.id,
                            addItem?.name?:itemText.value,
                            addItem?.isCheck ?: false,
                            listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
                updateShoppingListCount()
            }

            is
            AddItemEvent.OnShowEditDialog -> {
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
                updateShoppingListCount()
            }

            is AddItemEvent.OncheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
                updateShoppingListCount()
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
                addItem = addItem?.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.OnItemSave)
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }

    private fun updateShoppingListCount() {
        viewModelScope.launch {
            itemList?.collect { list ->
                var counter: Int = 0
                list.forEach { item ->
                    if (item.isCheck) counter++
                }
                shoppingListItem?.copy(allItemsCount = list.size, allSelectedItemsCount = counter)
                    ?.let { shItem ->
                        repository.insertItem(
                            shItem

                        )
                    }
            }
        }
    }
    private fun SendUiEvent(event: UiEvent){
        viewModelScope.launch { _uiEvent.send(event) }
    }
}