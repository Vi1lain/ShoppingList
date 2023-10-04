package Vi1ain.My.Application.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_list_name")
data class ShoppingListItem(
    @PrimaryKey
    val id:Int? = null,
    val name: String,
    val time:String,
    val allItemsCount:Int,
    val allSelectedItemsCount:Int,

)
