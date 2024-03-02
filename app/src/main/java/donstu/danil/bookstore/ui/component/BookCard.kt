package donstu.danil.bookstore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.R
import donstu.danil.bookstore.ui.theme.BookstoreTheme

@Composable
fun BookCard(book: BookModel, onClick: () -> Unit) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick).padding(4.dp, 8.dp)
    ) {
        Box (
            contentAlignment = Alignment.Center
        ) {
            val painter = rememberAsyncImagePainter(context.getString(R.string.image_url) + book.image)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(4.dp))
                ,
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                )
            }
        }
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
//            items(offlineBooks) { book -> BookCard(book, {}) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookstoreTheme {
        Box(modifier = Modifier.width(240.dp)) {
//            BookCard(offlineBooks[0], {})
        }
    }
}