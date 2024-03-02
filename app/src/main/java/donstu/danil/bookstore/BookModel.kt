package donstu.danil.bookstore

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    val id: Int,
    val name: String,
    @SerialName("type_id")
    val typeId: String = "unknown",
    @SerialName("book_type_name")
    val typeName: String = "Unknown",
    val price: Int,
    val rating: Int,
    val image: String
)