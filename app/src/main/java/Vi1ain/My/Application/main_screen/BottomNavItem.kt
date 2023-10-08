package Vi1ain.My.Application.main_screen

import Vi1ain.My.Application.R
import Vi1ain.My.Application.utils.Routes

sealed class BottomNavItem(val title:String, val iconId:Int, val route:String ){
    object ListItem:BottomNavItem("List", R.drawable.list_icon, Routes.SHOPPING_LIST)
    object NoteItem:BottomNavItem("Note", R.drawable.note_icon, Routes.NOTE_LIST)
    object AboutItem:BottomNavItem("About", R.drawable.about_icon, Routes.ABOUT)
    object SettingItem:BottomNavItem("Setting", R.drawable.setting_icon, Routes.SETTINGS)
}
