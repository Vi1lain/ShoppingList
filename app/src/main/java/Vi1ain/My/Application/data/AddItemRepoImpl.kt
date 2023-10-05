package Vi1ain.My.Application.data

import kotlinx.coroutines.flow.Flow

class AddItemRepoImpl(private val dao: AddItemDao):AddItemRepository {
    override suspend fun insertItem(item: AddItem) {
        dao.insertItem(item)
    }

    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: AddItem) {
        dao.deleteItem(item)
    }

    override fun getAllItemsBid(listId: Int): Flow<List<AddItem>> {
        return dao.getAllItemsBid(listId)
    }

    override suspend fun getListItemsBid(listId: Int): ShoppingListItem {
        return dao.getListItemsBid(listId)
    }

}