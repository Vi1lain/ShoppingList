package Vi1ain.My.Application.data

import kotlinx.coroutines.flow.Flow

interface NoteItemRepository {

    suspend fun insertItem(item: NoteItem)

    suspend fun deleteItem(item: NoteItem)

    fun getAllNote(): Flow<List<NoteItem>>

    suspend fun getNoteItemBid(id: Int): NoteItem
}