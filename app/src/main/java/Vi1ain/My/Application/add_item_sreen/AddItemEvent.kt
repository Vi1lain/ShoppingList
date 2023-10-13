package Vi1ain.My.Application.add_item_sreen

import Vi1ain.My.Application.data.AddItem
import Vi1ain.My.Application.shopping_list_sreen.ShoppingListEvent

sealed class AddItemEvent {
    data class OnDelete(val item: AddItem) : AddItemEvent()
    data class OnShowEditDialog(val item: AddItem) : AddItemEvent()
    data class OnTextChange(val text: String) : AddItemEvent()
    data class OncheckedChange(val item: AddItem) : AddItemEvent()
    object OnItemSave: AddItemEvent()
}
