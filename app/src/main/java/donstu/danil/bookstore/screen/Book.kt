package donstu.danil.bookstore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import donstu.danil.bookstore.BookModel
import donstu.danil.bookstore.R
import donstu.danil.bookstore.ui.component.Rating
import donstu.danil.bookstore.ui.theme.BookstoreTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class BookResponse (
    val book: BookModel
)

@Composable
fun Book(bookId: Int, modifier: Modifier) {
    val context = LocalContext.current

    var text by remember { mutableStateOf("no text") }
    val queue by remember { mutableStateOf(Volley.newRequestQueue(context)) }

    var bookResponse by remember { mutableStateOf<BookResponse?>(null) }
    val book = remember(bookResponse) { bookResponse?.book }

    LaunchedEffect(null) {
        val stringRequest = object : StringRequest(
            Method.GET, context.getString(R.string.api_url) + "book/$bookId",
            { response ->
                text = "Response is: ${response.substring(0, response.length.coerceAtMost(500))}"
                bookResponse = Json.decodeFromString<BookResponse>(response)
                println(bookResponse)
            },
            { error -> text = error.message.toString() }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return hashMapOf("Accept" to "application/json")
            }
        }
        queue.add(stringRequest)
    }

    if(book == null) {
        Box(
            Modifier.fillMaxWidth(),
            Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp),
            )
        }
        return
    }

    Surface {
        Column(
            modifier = modifier.padding(2.dp).fillMaxWidth()
        ) {
            // image block
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box (
                    contentAlignment = Alignment.Center
                ) {
                    val painter = rememberAsyncImagePainter(context.getString(R.string.image_url) + book.image)
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
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
                Row(
                    modifier = Modifier.padding(top = 12.dp, bottom = 6.dp, start = 4.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        tonalElevation = 40.dp,
                        modifier = Modifier
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp)),
                    ) {
                        Rating(
                            rating = book.rating.toString(),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "-23%", color = Color.White, modifier =
                            Modifier
                                .background(color = Color.Red, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 0.7.dp)
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "0% возвратов >", color = Color(0xFF39E640),
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
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                tonalElevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = book.name, fontWeight = FontWeight.Medium, fontSize = 4.5.em)
                    Surface(
                        tonalElevation = 10.dp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    ) {
                        Surface(
                            tonalElevation = 10.dp,
                            modifier = Modifier
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp)),
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
                                }

                                Surface(tonalElevation = 20.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                                    ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        val color = Color(0xFF2FAF2F)
                                        Icon(
                                            Icons.Filled.ArrowDropDown,
                                            contentDescription = null,
                                            Modifier.size(20.dp),
                                            tint = color
                                        )
                                        Text(text = "67 ₽", color = color, fontWeight = FontWeight.Medium)
                                    }
                                }
                                Column(
                                    modifier = Modifier.padding(top = 4.dp)
                                ) {
                                    val fontSize = 2.5.em
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ){
                                        Text(text = "15 сентября", fontSize = fontSize)
                                        Text(text = "доставка со склада", fontSize = fontSize, color = Color.Gray)
                                    }
                                    Text(text = "Главный склад", fontSize = fontSize, color = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }

            Surface(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Дополнительная информация")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Тип книги")
                            Text(text = "Рейтинг")
                            Text(text = "Цена")
                            Text(text = "Количество страниц")
                        }
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = book.typeName)
                            Text(text = book.rating.toString())
                            Text(text = book.price.toString())
                            Text(text = "159")
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = null,
                        Modifier
                            .size(35.dp)
                            .background(shape = RoundedCornerShape(8.dp), color = Color(0xFFDBD9D9))
                        ,
                        tint = Color.Gray
                    )
                    Column {
                        Text(text = "Много книг", fontWeight = FontWeight.Medium, fontSize = 3.em)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Rating(rating = "4.9")
                            Text(text = "• 469 392 оценки", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xA61E1F1E, widthDp = 370)
fun BookPreview() {
    BookstoreTheme(darkTheme = true) {
//        Book(bookId = offlineBooks[0], modifier = Modifier.padding(0.dp))
    }
}