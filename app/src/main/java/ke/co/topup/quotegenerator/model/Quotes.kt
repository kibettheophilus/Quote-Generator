package ke.co.topup.quotegenerator.model

import com.google.gson.annotations.SerializedName

class Quotes(
    @SerializedName("author")
    val author: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("quote")
    val quote: String?
)
