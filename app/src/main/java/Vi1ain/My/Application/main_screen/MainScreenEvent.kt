package Vi1ain.My.Application.main_screen

import Vi1ain.My.Application.data.ShoppingListItem
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListEvent

sealed class MainScreenEvent{
    object OnShowEditDialog: MainScreenEvent()
    object OnItemSave: MainScreenEvent()
    data class Navigate(val route:String): MainScreenEvent()
    data class NavigateMain(val route:String): MainScreenEvent()
}
