package Vi1ain.My.Application.data

import kotlinx.coroutines.flow.Flow

class ShopingListRepoImpl(private val dao: ShoppingListDao):ShoppingListRepository {
    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingListItem) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<ShoppingListItem>> {
       return dao.getAllItems()
    }

}