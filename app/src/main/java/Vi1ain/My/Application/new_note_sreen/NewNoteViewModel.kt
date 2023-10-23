package Vi1ain.My.Application.new_note_sreen

import Vi1ain.My.Application.data.NoteItem
import Vi1ain.My.Application.data.NoteItemRepository
import Vi1ain.My.Application.utils.UiEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var noteId = -1
    private var noteItem: NoteItem? = null

    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt()?:-1
        if (noteId != -1) {
            viewModelScope.launch {
                repository.getNoteItemBid(noteId).let { noteItem ->
                    title = noteItem.title
                    description = noteItem.description
                    this@NewNoteViewModel.noteItem = noteItem
                }
            }
        }
    }
    fun OnEvent(event:NewNoteEvent){
        when(event){
            is NewNoteEvent.OnSave->{
                viewModelScope.launch {
                    repository.insertItem(
                        NoteItem(noteItem?.id, title, description, time = "02/08/1988")
                    )
                }
                SendUiEvent(UiEvent.PopBackStack)
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }

            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
        }

    }

    private fun SendUiEvent(event: UiEvent){
        viewModelScope.launch { _uiEvent.send(event) }
    }
}