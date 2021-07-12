package ke.co.topup.quotegenerator.model

import com.google.gson.annotations.SerializedName

class Quotes(
    val author: String?,
    @SerializedName("text")
    val quote: String?
)
