package ke.co.topup.quotegenerator.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //exact endpoint of your api
    @GET("random")
    suspend fun getRandomQuote() : Quotes
}