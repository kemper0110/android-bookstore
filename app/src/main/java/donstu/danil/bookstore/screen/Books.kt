package donstu.danil.bookstore.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import donstu.danil.bookstore.books
import donstu.danil.bookstore.ui.component.BookCard

@Composable
fun Books(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight(),
        columns = GridCells.Fixed(2)
    ) {
        items(books) { book -> BookCard(book) }
    }
}