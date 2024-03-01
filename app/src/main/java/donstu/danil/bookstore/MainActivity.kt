package donstu.danil.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import donstu.danil.bookstore.ui.component.BookCard
import donstu.danil.bookstore.ui.theme.BookstoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookstoreTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Главная")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,

                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(Icons.Filled.Home, contentDescription = "Все книги")
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Edit,
                                        contentDescription = "Художественная литература",
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Face,
                                        contentDescription = "Комиксы",
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Build,
                                        contentDescription = "Образовательная литература",
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    LazyVerticalGrid(
                        modifier = Modifier.padding(innerPadding),
                        columns = GridCells.Fixed(2)
                    ) {
                        items(books) { book -> BookCard(book) }
                    }
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