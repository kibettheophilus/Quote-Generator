package ke.co.topup.quotegenerator.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAdapter {

    val apiClient : ApiService = Retrofit.Builder()
        .baseUrl("https://web-series-quotes.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}