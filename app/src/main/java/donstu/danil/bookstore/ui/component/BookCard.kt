package donstu.danil.bookstore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
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
        Modifier.fillMaxWidth(),
    ) {
        Image(painter = ColorPainter(Color.Red), "Абоба", Modifier.height(Dp(500F)))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(Dp(8F))
        ) {
            Text(
                text = book.price.toString() + " ₽",
                fontSize = TextUnit(4.5F, TextUnitType.Em),
                fontWeight = FontWeight.SemiBold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = book.name)
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dp(4F))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tut), "",
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
fun BookCardPreview() {
    val book = books[0]
    BookstoreTheme {
        BookCard(
            book = book
        )
    }
}