package Vi1ain.My.Application.dialog

sealed class DialgoEvent {
    data class TextChange(val teext: String):DialgoEvent()
    object OnCansel:DialgoEvent()
    object OnConfirm:DialgoEvent()
}
