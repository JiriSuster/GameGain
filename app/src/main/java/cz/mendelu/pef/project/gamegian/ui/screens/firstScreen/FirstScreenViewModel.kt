package cz.mendelu.pef.project.gamegian.ui.screens.firstScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    val myDataStore: MyDataStore
) : ViewModel() {

    fun updateUsername(username: String) {
        viewModelScope.launch {
            myDataStore.updateUsername(username)
        }
    }

    fun updateGender(isMale: Boolean) {
        viewModelScope.launch {
            myDataStore.updateGender(isMale)
        }
    }
}
