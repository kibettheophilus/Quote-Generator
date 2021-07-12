package ke.co.topup.quotegenerator.network

import ke.co.topup.quotegenerator.model.Quotes
import retrofit2.http.GET

interface ApiService {
    //exact endpoint of your api
    @GET(".json")
    suspend fun getRandomQuote() : Quotes
}