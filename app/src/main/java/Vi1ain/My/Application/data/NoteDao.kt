package Vi1ain.My.Application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: NoteItem)

    @Delete
    suspend fun deleteItem(item: NoteItem)

    @Query("SELECT * FROM note_table")
    fun getAllNote(): Flow<List<NoteItem>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteItemBid(id: Int): NoteItem

}