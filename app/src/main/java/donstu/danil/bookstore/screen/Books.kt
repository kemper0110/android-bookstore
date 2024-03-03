package donstu.danil.bookstore.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.rest.rememberQuery
import donstu.danil.bookstore.ui.component.BookCard
import donstu.danil.bookstore.ui.widget.NoDataView
import kotlinx.serialization.Serializable


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

    val (booksResponse, error) = rememberQuery<BooksResponse>(url = "")
    val books = remember(booksResponse) { booksResponse?.books } ?: return NoDataView(error)

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
}