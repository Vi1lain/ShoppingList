package Vi1ain.My.Application.di

import Vi1ain.My.Application.data.AddItemRepoImpl
import Vi1ain.My.Application.data.AddItemRepository
import Vi1ain.My.Application.data.MainDB
import Vi1ain.My.Application.data.NoteItemRepoImpl
import Vi1ain.My.Application.data.NoteItemRepository
import Vi1ain.My.Application.data.ShopingListRepoImpl
import Vi1ain.My.Application.data.ShoppingListRepository
import Vi1ain.My.Application.data_store.DataStoreManager
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDB {
        return Room.databaseBuilder(
            app, MainDB::class.java, "shop_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(db: MainDB): ShoppingListRepository {
        return ShopingListRepoImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideAddItemRepository(db: MainDB): AddItemRepository {
        return AddItemRepoImpl(db.addItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteItemRepository(db: MainDB): NoteItemRepository {
        return NoteItemRepoImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app: Application): DataStoreManager {
        return DataStoreManager(app)
    }
}
