package donstu.danil.bookstore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import donstu.danil.bookstore.Book
import donstu.danil.bookstore.R
import donstu.danil.bookstore.books
import donstu.danil.bookstore.ui.theme.BookstoreTheme

@Composable
fun BookCard(book: Book) {
    Column(
    ) {
        Image(painter = painterResource(id = R.drawable.p1), "Абоба")
        Column(
            Modifier
                .padding(Dp(8F))
        ) {
            Text(
                text = book.price.toString() + " ₽",
                fontSize = TextUnit(4.5F, TextUnitType.Em),
                fontWeight = FontWeight.SemiBold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = book.name)
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dp(4F))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star), "",
                        Modifier
                            .width(Dp(12F))
                            .height(Dp(12F))
                    )
                    Text(text = book.rating.toString())
                }
            }
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
        BookCard(books[0])
    }
}