package Vi1ain.My.Application.utils

object ProgressHelper {
    fun GetProgress(allItemsCount: Int, selectedItemsCount: Int): Float {
        return if (selectedItemsCount == 0) 0.0f
        else selectedItemsCount.toFloat() / allItemsCount.toFloat()
    }
}