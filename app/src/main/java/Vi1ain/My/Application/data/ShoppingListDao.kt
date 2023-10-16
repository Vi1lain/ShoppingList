package Vi1ain.My.Application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("DELETE  FROM add_item WHERE listId = :listId")
    suspend fun deleteAddItem(listId: Int)

    @Query("SELECT * FROM shopping_list_name")
    fun getAllItems(): Flow<List<ShoppingListItem>>
    @Transaction
    suspend fun deleteShoppingList(item:ShoppingListItem){
        deleteAddItem(item.id!!)
        deleteItem(item)
    }
}