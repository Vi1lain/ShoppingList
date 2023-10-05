package Vi1ain.My.Application.data

import kotlinx.coroutines.flow.Flow

interface AddItemRepository {

    suspend fun insertItem(item: AddItem)

    suspend fun deleteItem(item: AddItem)

    fun getAllItemsBid(listId: Int): Flow<List<AddItem>>

    suspend fun getListItemsBid(listId: Int): ShoppingListItem

    suspend fun insertItem(item: ShoppingListItem)
}