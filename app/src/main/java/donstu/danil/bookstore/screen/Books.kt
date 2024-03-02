package donstu.danil.bookstore.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.R
import donstu.danil.bookstore.ui.component.BookCard
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class BooksResponse(
    val books: Array<BookModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BooksResponse

        return books.contentEquals(other.books)
    }

    override fun hashCode(): Int {
        return books.contentHashCode()
    }
}

@Composable
fun Books(modifier: Modifier, onNavigate: (Int) -> Unit) {
    val gridState = rememberLazyGridState()

    val context = LocalContext.current
    var text by remember { mutableStateOf("no text") }
    val queue by remember { mutableStateOf(Volley.newRequestQueue(context)) }

    var booksResponse by remember { mutableStateOf<BooksResponse?>(null) }
    val books = remember(booksResponse) { booksResponse?.books }

    LaunchedEffect(null) {
        val stringRequest = object : StringRequest(
            Method.GET, context.getString(R.string.api_url),
            { response ->
                text = "Response is: ${response.substring(0, response.length.coerceAtMost(500))}"
                booksResponse = Json.decodeFromString<BooksResponse>(response)
                println(booksResponse)
            },
            { error -> text = error.message.toString() }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return hashMapOf("Accept" to "application/json")
            }
        }
        queue.add(stringRequest)
    }

    if (books != null) {
        LazyVerticalGrid(
            state = gridState,
            modifier = modifier.fillMaxHeight(),
            columns = GridCells.Fixed(2)
        ) {
            items(
                books, key = { book -> book.id }) { book ->
                BookCard(book) { onNavigate(book.id) }
            }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp),
            )
        }
    }
}