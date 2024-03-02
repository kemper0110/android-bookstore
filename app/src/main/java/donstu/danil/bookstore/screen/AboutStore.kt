package donstu.danil.bookstore.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun AboutStore(modifier: Modifier) {
    val context = LocalContext.current
    val onMapClick = {
        val uri = Uri.parse("yandexmaps://maps.yandex.ru/?ll=39.725640%2C47.223718&mode=poi&poi%5Bpoint%5D=39.725287%2C47.223684&poi%5Buri%5D=ymapsbm1%3A%2F%2Forg%3Foid%3D1370508591&z=17.8")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
    Column(
        modifier = modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        Text(
            text = """
                                «Читай-город» – это самая большая в России сеть книжных магазинов и интернет-магазин. Компания входит в объединённую розничную сеть «Читай-город» – «Гоголь-Моголь» – «Буквоед».

                                Мы не просто продаём книги, а разделяем любовь наших покупателей к чтению. Нам знакомо чувство, когда хорошие романы заканчиваются слишком быстро, времени в дороге не хватает, чтобы дочитать главу, а героиня никак не может найти свою любовь. Мы знаем, как быстро летит время в компании с новинкой любимого автора и как сильно хочется растянуть это удовольствие.

                                Помимо книг в «Читай-город» можно найти канцтовары, сладости, подарочную упаковку и идеи для сюрпризов близким. Мы сами разрабатываем дизайны для многих ежедневников, закладок, товаров для творчества и других интересных вещей, поэтому кроме как в «Читай-город» их больше нигде не найти.
                                """.trimIndent()
        )
        Text(
            text = "Информация о компании",
            fontSize = 4.5.em,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = "Название магазина")
                Text(text = "Адрес магазина")
                Text(text = "Номер телефона")
            }
            Column {
                Text(text = "ООО \"Много Книг\"")
                Text(text = "Большая Садовая 110")
                Text(text = "8 (800) 444-84-44")
            }
        }
        TextButton(onClick = onMapClick) {
            Text(text = "Мы на картах")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutStorePreview() {
    AboutStore(Modifier.padding(all = 10.dp))
}