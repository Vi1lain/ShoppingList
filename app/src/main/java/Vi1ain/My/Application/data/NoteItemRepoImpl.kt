package Vi1ain.My.Application.data

import kotlinx.coroutines.flow.Flow

class NoteItemRepoImpl(private val dao: NoteDao):NoteItemRepository {
    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getAllNote(): Flow<List<NoteItem>> {
        return dao.getAllNote()
    }

    override suspend fun getNoteItemBid(id: Int): NoteItem {
        return dao.getNoteItemBid(id)
    }

}