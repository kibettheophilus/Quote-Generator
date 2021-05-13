package ke.co.topup.quotegenerator.network

import retrofit2.http.GET

interface ApiService {
    //exact endpoint of your api
    @GET("random")
    suspend fun getRandomQuote() : Quotes
}