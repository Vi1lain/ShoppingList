package Vi1ain.My.Application.note_list_sreen

import Vi1ain.My.Application.data.NoteItem

sealed class NoteListEvent{
    data class OnShowDeleteDialog(val item:NoteItem):NoteListEvent()
    data class OnItemClick(val route:String):NoteListEvent()
    object OnDoneDeleteItem :NoteListEvent()
}
