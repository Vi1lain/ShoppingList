package Vi1ain.My.Application.new_note_sreen

sealed class NewNoteEvent{
    data class OnTitleChange(val title:String):NewNoteEvent()
    data class OnDescriptionChange(val description:String):NewNoteEvent()
    object OnSave:NewNoteEvent()
}
