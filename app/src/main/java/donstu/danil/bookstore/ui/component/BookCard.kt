package donstu.danil.bookstore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import donstu.danil.bookstore.Book
import donstu.danil.bookstore.R
import donstu.danil.bookstore.books
import donstu.danil.bookstore.ui.theme.BookstoreTheme

@Composable
fun Rating(rating: String) {
    Row(
        modifier = Modifier.width(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.star), "",
            Modifier
                .width(14.dp)
                .height(14.dp)
        )
        Text(text = rating)
    }
}

@Composable
fun BookCard(book: Book) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.p1), "Абоба")
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
            items(books) { book -> BookCard(book) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookstoreTheme {
        Box(modifier = Modifier.width(240.dp)) {
            BookCard(books[0])
        }
    }
}