package Vi1ain.My.Application.data

import androidx.room.Entity

@Entity(tableName = "note_table")
data class NoteItem(
    val id: Int? = null,
    val title: String,
    val description:String,
    val time:Int,

)
