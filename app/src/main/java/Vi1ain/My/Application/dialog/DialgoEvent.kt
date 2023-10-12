package Vi1ain.My.Application.dialog

sealed class DialgoEvent {
    data class TextChange(val text: String):DialgoEvent()
    object OnCansel:DialgoEvent()
    object OnConfirm:DialgoEvent()
}
