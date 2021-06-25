package ke.co.topup.quotegenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.topup.quotegenerator.network.ApiService
import ke.co.topup.quotegenerator.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    fun getRandomQuote() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getRandomQuote()))

        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Unable to fetch random quotes"
                )
            )

        }
    }
}