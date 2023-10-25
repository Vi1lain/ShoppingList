package Vi1ain.My.Application.note_list_sreen

import Vi1ain.My.Application.data.NoteItem
import Vi1ain.My.Application.data.NoteItemRepository
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
class NoteListViewModel @Inject constructor(
    private val repository: NoteItemRepository
) : ViewModel(), DialogController {
    val noteList = repository.getAllItems()
    private var noteItem: NoteItem? = null
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("Delete this note ")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    private fun SendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }

    fun OnEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.OnItemClick -> {
                SendUiEvent(UiEvent.Navigate(event.route))
            }

            is NoteListEvent.OnShowDeleteDialog -> {
                openDialog.value = true
                noteItem = event.item
            }

            is NoteListEvent.OnDoneDeleteItem -> {
                viewModelScope.launch { repository.insertItem(noteItem!!) }
            }
        }
    }

    override fun OnDialogEvent(event: DialgoEvent) {
        when (event) {
            is DialgoEvent.OnCansel -> {
                openDialog.value = false
            }

            is DialgoEvent.OnConfirm -> {
                viewModelScope.launch {
                    repository.deleteItem(noteItem!!)
                    SendUiEvent(UiEvent.ShowSnackBar("Undone delete item?"))
                }
                openDialog.value = false
            }

            else -> {}
        }
    }


}