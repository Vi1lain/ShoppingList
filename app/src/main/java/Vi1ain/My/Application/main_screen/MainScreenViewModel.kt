package Vi1ain.My.Application.main_screen

import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.data.ShoppingListRepository
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListEvent
import Vi1ain.My.Application.utils.UiEvent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShoppingListRepository,

    ) : ViewModel(), DialogController {
    override var dialogTitle = mutableStateOf("List name: ")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            null,
                            editableText.value,
                            "02-08-1988",
                            0,
                            0
                        )
                    )
                }
            }



            is MainScreenEvent.OnShowEditDialog -> {

                openDialog.value = true
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
onEvent(MainScreenEvent.OnItemSave)
                openDialog.value=false
                editableText.value = ""
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }
}