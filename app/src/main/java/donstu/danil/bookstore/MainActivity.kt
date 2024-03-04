package donstu.danil.bookstore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import donstu.danil.bookstore.screen.AboutMe
import donstu.danil.bookstore.screen.AboutStore
import donstu.danil.bookstore.screen.BookScreen
import donstu.danil.bookstore.screen.Books
import donstu.danil.bookstore.ui.theme.BookstoreTheme

val LocalRequestQueue =
    compositionLocalOf<RequestQueue> { error("`RequestQueue` is not initialized in `compositionLocalOf`") }

@Composable
fun BottomBar(navigate: (url: String) -> Unit, selectedUrl: String) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navigate("/") }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = null,
                    tint = if (selectedUrl == "/") Color.White else LocalContentColor.current
                )
            }
            IconButton(onClick = { navigate("/about-store") }) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = null,
                    tint = if (selectedUrl == "/about-store") Color.White else LocalContentColor.current
                )
            }
            IconButton(onClick = { navigate("/about-me") }) {
                Icon(
                    Icons.Filled.Face,
                    contentDescription = null,
                    tint = if (selectedUrl == "/about-me") Color.White else LocalContentColor.current
                )
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val requestQueue = Volley.newRequestQueue(applicationContext)
            CompositionLocalProvider(LocalRequestQueue provides requestQueue) {
                val navController = rememberNavController()
                BookstoreTheme {
                    NavHost(navController, startDestination = "/", Modifier.fillMaxHeight()) {
                        composable("/") {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        colors = topAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            titleContentColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        title = { Text("Книги") }
                                    )
                                },
                                bottomBar = {
                                    BottomBar({ navController.navigate(it) }, "/")
                                }
                            ) { innerPadding ->
                                Books(Modifier.padding(innerPadding)) { bookId ->
                                    navController.navigate("/book/$bookId")
                                }
                            }
                        }
                        composable("/about-store") {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        colors = topAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            titleContentColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        title = { Text("О магазине") }
                                    )
                                },
                                bottomBar = { BottomBar({ navController.navigate(it) }, "/about-store") }
                            ) { innerPadding ->
                                AboutStore(Modifier.padding(innerPadding))
                            }
                        }
                        composable("/about-me") {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        colors = topAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            titleContentColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        title = { Text("Об авторе") }
                                    )
                                },
                                bottomBar = { BottomBar({ navController.navigate(it) }, "/about-me") }
                            ) { innerPadding ->
                                AboutMe(Modifier.padding(innerPadding))
                            }
                        }
                        composable(
                            "/book/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { navBackStackEntry ->
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        colors = topAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            titleContentColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        title = { Text("Книга") }
                                    )
                                },
                                bottomBar = { BottomBar({ navController.navigate(it) }, "/") }
                            ) { innerPadding ->
                                navBackStackEntry.arguments?.let { args ->
                                    BookScreen(args.getInt("id"), Modifier.padding(innerPadding))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}