package Vi1ain.My.Application.main_screen

import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.data.ShoppingListRepository
import Vi1ain.My.Application.dialog.DialgoEvent
import Vi1ain.My.Application.dialog.DialogController
import Vi1ain.My.Application.utils.Routes
import Vi1ain.My.Application.utils.UiEvent
import Vi1ain.My.Application.utils.getCurrentTime
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShoppingListRepository,

    ) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("List name: ")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set
    var showFloatingButtom = mutableStateOf(true)
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
                            getCurrentTime(),
                            0,
                            0
                        )
                    )
                }
            }


            is MainScreenEvent.OnNewItemClick -> {
                if (event.route == Routes.SHOPPING_LIST) {
                    openDialog.value = true
                } else {
                    SendUiEvent(UiEvent.NavigateMain(Routes.NEW_NOTE + "/-1"))
                }
            }

            is MainScreenEvent.Navigate -> {
                SendUiEvent(UiEvent.Navigate(event.route))
                showFloatingButtom.value =
                    if (event.route == Routes.ABOUT || event.route == Routes.SETTINGS) {
                        false
                    } else {
                        true
                    }
            }

            is MainScreenEvent.NavigateMain -> {
                SendUiEvent(UiEvent.NavigateMain(event.route))
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
                openDialog.value = false
                editableText.value = ""
            }

            is DialgoEvent.TextChange -> {
                editableText.value = event.text
            }
        }
    }

    private fun SendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }
}