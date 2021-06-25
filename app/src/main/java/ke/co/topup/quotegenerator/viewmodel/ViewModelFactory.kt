package ke.co.topup.quotegenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ke.co.topup.quotegenerator.network.ApiService
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val apiService: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class Name")
    }
}