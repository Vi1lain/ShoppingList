package Vi1ain.My.Application.main_screen

import Vi1ain.My.Application.R

sealed class BottomNavItem(val title:String, val iconId:Int, val route:String ){
    object ListItem:BottomNavItem("List", R.drawable.list_icon, "route1")
    object NoteItem:BottomNavItem("Note", R.drawable.note_icon, "route2")
    object AboutItem:BottomNavItem("About", R.drawable.about_icon, "route3")
    object SettingItem:BottomNavItem("Setting", R.drawable.setting_icon, "route4")
}
