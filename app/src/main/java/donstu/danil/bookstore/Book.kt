package donstu.danil.bookstore

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val name: String,
    val type_id: String,
    val price: Int,
    val rating: Int,
    val image: String
)