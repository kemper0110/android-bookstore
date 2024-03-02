package donstu.danil.bookstore

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val name: String,
    @SerialName("type_id")
    val typeId: String,
    val price: Int,
    val rating: Int,
    val image: String
)