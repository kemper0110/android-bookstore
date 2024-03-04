package donstu.danil.bookstore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.R
import donstu.danil.bookstore.rest.rememberQuery
import donstu.danil.bookstore.ui.theme.BookstoreTheme
import donstu.danil.bookstore.ui.widget.NoDataView
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val book: BookModel
)

@Composable
fun BookScreen(bookId: Int, modifier: Modifier) {
    val (bookResponse, error) = rememberQuery<BookResponse>(url = "book/$bookId")
    val book = remember(bookResponse) { bookResponse?.book } ?: return NoDataView(error)

    Book(book, modifier)
}

@Composable
fun Book(book: BookModel, modifier: Modifier) {
    Surface {
        Column(
            modifier = modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(2.dp, bottom = 32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ImageBlock(book)
            MainInformation(book)
            ExtraInformation(book)
        }
    }
}

val tonalElevationLevels = arrayListOf(
    0.dp, 2.dp, 8.dp
)
val roundedCornerLevels = arrayListOf(
    0.dp, 24.dp, 16.dp
)

@Composable
fun ImageBlock(book: BookModel) {
    val context = LocalContext.current
    Surface(
        tonalElevation = tonalElevationLevels[1],
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = roundedCornerLevels[1],
                    bottomEnd = roundedCornerLevels[1]
                )
            )
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center
            ) {
                val painter: Painter
                if (LocalView.current.isInEditMode) {
                    painter = painterResource(R.drawable.p1)
                } else {
                    painter =
                        rememberAsyncImagePainter(context.getString(R.string.image_url) + book.image)
                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp),
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(400.dp),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = 14.dp,
                        bottom = 10.dp,
                        start = 14.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    tonalElevation = 40.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp)),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star), "",
                            Modifier
                                .width(14.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = book.rating.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 3.3.em
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = "-23%",
                        fontSize = 3.3.em,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White, modifier =
                        Modifier
                            .background(color = Color.Red)
                            .clip(RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 0.7.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = "0% возвратов", color = Color(0xFF39E640),
                        modifier =
                        Modifier
                            .background(
                                color = Color(0xA633691E),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 0.7.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MainInformation(book: BookModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundedCornerLevels[1])),
        tonalElevation = tonalElevationLevels[1]
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                Text(text = book.name, fontWeight = FontWeight.Medium, fontSize = 4.5.em)
                Surface(
                    tonalElevation = tonalElevationLevels[2],
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(roundedCornerLevels[2]))
                ) {
                    Surface(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = book.price.toString() + " ₽",
                                    fontSize = 4.5.em,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "1404 ₽",
                                    fontSize = 3.em,
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val color = Color(0xFF2FAF2F)
                                    Icon(
                                        Icons.Filled.ArrowDropDown,
                                        contentDescription = null,
                                        Modifier.size(20.dp),
                                        tint = color
                                    )
                                    Text(
                                        text = "67 ₽",
                                        color = color,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                val fontSize = 2.5.em
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(text = "15 сентября", fontSize = fontSize)
                                    Text(
                                        text = "доставка со склада",
                                        fontSize = fontSize,
                                        color = Color.Gray
                                    )
                                }
                                Text(
                                    text = "Главный склад",
                                    fontSize = fontSize,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
            Surface(
                tonalElevation = tonalElevationLevels[2],
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(roundedCornerLevels[2]))
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = null,
                        Modifier
                            .size(35.dp)
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFDBD9D9)
                            ),
                        tint = Color.Gray
                    )
                    Column {
                        Text(text = "Много книг", fontWeight = FontWeight.Medium, fontSize = 3.3.em)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star), "",
                                    Modifier
                                        .width(12.dp)
                                        .height(12.dp)
                                )
                                Text(text = "4.9", fontSize = 3.3.em)
                            }
                            Text(text = "• 469 392 оценки", color = Color.Gray, fontSize = 3.3.em)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExtraInformation(book: BookModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundedCornerLevels[1])),
        tonalElevation = tonalElevationLevels[1],
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            @Composable
            fun InfoTitle(text: String) {
                Text(
                    text,
                    fontSize = 3.3.em,
                    color = Color(0xFFA8A8A8),
                    fontWeight = FontWeight.Medium
                )
            }

            @Composable
            fun InfoValue(text: String) {
                Text(text, fontSize = 3.3.em, textAlign = TextAlign.End)
            }

            @Composable
            fun InfoRow(content: @Composable RowScope.() -> Unit) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = content
                )
            }

            @Composable
            fun InfoDivider() {
                // background = 0xff20292e
                Divider(
                    modifier = Modifier.padding(top = 4.dp, bottom = 3.dp),
                    color = Color(0xFF37444B)
                )
            }
            InfoRow {
                InfoTitle(text = "Тип книги")
                InfoValue(text = book.typeName)
            }
            InfoDivider()
            InfoRow {
                InfoTitle(text = "Рейтинг")
                InfoValue(text = book.rating.toString())
            }
            InfoDivider()
            InfoRow {
                InfoTitle(text = "Цена")
                InfoValue(text = book.price.toString())
            }
            InfoDivider()
            InfoRow {
                InfoTitle(text = "Количество страниц")
                InfoValue(text = "159")
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xA61E1F1E, widthDp = 370)
fun BookPreview() {
    val book = BookModel(
        1, "Виленкин Математика",
        "", "Образовательная литература",
        1599, 10, ""
    )
    BookstoreTheme(darkTheme = true) {
        Book(book, Modifier.padding(0.dp))
    }
}