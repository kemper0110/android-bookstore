package donstu.danil.bookstore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.R
import donstu.danil.bookstore.books
import donstu.danil.bookstore.ui.theme.BookstoreTheme

@Composable
fun BookCard(book: BookModel, onClick: () -> Unit) {
    val image = R.drawable::class.java.fields.filter { it.name.equals(book.image.split(".").first()) }.map {
        LocalContext.current.resources.getIdentifier(
            it.name,
            "drawable",
            LocalContext.current.packageName
        )
    }.first()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(painter = painterResource(id = image), "Абоба")
        Column(
            Modifier
                .padding(Dp(8F))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = book.price.toString() + " ₽",
                    fontSize = 4.5.em,
                    fontWeight = FontWeight.SemiBold
                )
                Rating(rating = book.rating.toString())
            }
            Text(text = book.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardListPreview() {
    BookstoreTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(books) { book -> BookCard(book, {}) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookstoreTheme {
        Box(modifier = Modifier.width(240.dp)) {
            BookCard(books[0], {})
        }
    }
}