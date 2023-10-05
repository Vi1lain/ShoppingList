package Vi1ain.My.Application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItem)

    @Delete
    suspend fun deleteItem(item: AddItem)

    @Query("SELECT * FROM add_item WHERE listId = :listId")
    fun getAllItemsBid(listId: Int): Flow<List<AddItem>>

    @Query("SELECT * FROM shopping_list_name WHERE id = :listId")
    suspend fun getListItemsBid(listId: Int): ShoppingListItem

    @Update
    suspend fun insertItem(item: ShoppingListItem)
}