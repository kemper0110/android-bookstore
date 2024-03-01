package donstu.danil.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import donstu.danil.bookstore.ui.theme.BookstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookstoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookstoreTheme {
        Column {
            Greeting(name = "aboba")
            Greeting(name = "aboba")
        }
    }
}
// http request code

//    var once by remember { mutableStateOf(true) }
//    val context = LocalContext.current
//    val queue by remember { mutableStateOf(Volley.newRequestQueue(context)) }
//
//    if (once) {
//        val url = "https://www.google.com"
//        val stringRequest = StringRequest(Request.Method.GET, url,
//            { response -> text = "Response is: ${response.substring(0, 500)}" },
//            { text = "That didn't work!" }
//        )
//        queue.add(stringRequest)
//        once = false
//    }