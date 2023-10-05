package Vi1ain.My.Application.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoppingListItem::class, NoteItem::class, AddItem::class], version = 1)
abstract class MainDB : RoomDatabase() {
    abstract class shoppingListDao() : ShoppingListDao
    abstract class noteDao() : NoteDao
    abstract class addItemDao() : AddItemDao
}