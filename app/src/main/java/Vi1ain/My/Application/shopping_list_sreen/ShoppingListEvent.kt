package Vi1ain.My.Application.shopping_list_sreen

import Vi1ain.My.Application.data.ShoppingListItem

sealed class ShoppingListEvent{
    data class OnShowDeleteDialog(val item: ShoppingListItem):ShoppingListEvent()
    data class OnShowEditDialog(val item: ShoppingListItem):ShoppingListEvent()
    data class OnItemClick(val route: String):ShoppingListEvent()
    object OnItemSave:ShoppingListEvent()
}
