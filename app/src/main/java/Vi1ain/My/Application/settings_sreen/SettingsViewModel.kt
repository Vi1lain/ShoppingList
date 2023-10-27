package Vi1ain.My.Application.settings_sreen

import Vi1ain.My.Application.data_store.DataStoreManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    val colorItemListState = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {

            dataStoreManager.getStringPreference(DataStoreManager.TITLE_COLOR, "#0003CC")
                .collect { selectedColor ->

                    val tempColorItemList = ArrayList<ColorItem>()

                    ColorUtils.colorList.forEach { color ->
                        tempColorItemList.add(ColorItem(color, selectedColor == color))
                    }
                    colorItemListState.value = tempColorItemList
                }


        }

    }

    fun OnEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnItemSelected -> {
                viewModelScope.launch {
                    dataStoreManager.SaveStringPreference(event.color, DataStoreManager.TITLE_COLOR)
                }
            }
        }
    }
}