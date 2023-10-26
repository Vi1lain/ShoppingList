package Vi1ain.My.Application.settings_sreen

sealed class SettingsEvent{
    data class OnItemSelected(val color:String):SettingsEvent()

}
