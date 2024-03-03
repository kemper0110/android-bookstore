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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import donstu.danil.bookstore.screen.AboutMe
import donstu.danil.bookstore.screen.AboutStore
import donstu.danil.bookstore.screen.Book
import donstu.danil.bookstore.screen.Books
import donstu.danil.bookstore.ui.theme.BookstoreTheme

val LocalRequestQueue =
    compositionLocalOf<RequestQueue> { error("`RequestQueue` is not initialized in `compositionLocalOf`") }

class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val requestQueue = Volley.newRequestQueue(applicationContext)
            CompositionLocalProvider(LocalRequestQueue provides requestQueue) {
                BookstoreTheme {
                    val navController = rememberNavController()

                    data class NavItem(
                        val icon: ImageVector,
                        val path: String,
                        val title: String
                    )

                    val navItems = remember {
                        arrayListOf(
                            NavItem(icon = Icons.Filled.Home, path = "/", title = "Книги"),
                            NavItem(
                                icon = Icons.Filled.ShoppingCart,
                                path = "/about-store",
                                title = "О магазине"
                            ),
                            NavItem(
                                icon = Icons.Filled.Face,
                                path = "/about-me",
                                title = "Об авторе"
                            ),
                        )
                    }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    val currentNavItem =
                        navItems.find { navItem -> currentDestination?.hierarchy?.any { it.route == navItem.path } == true }
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    currentNavItem?.title?.let { Text(it) }
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
                                    navItems.forEach { navItem ->
                                        val selected =
                                            currentDestination?.hierarchy?.any { it.route == navItem.path } == true
                                        IconButton(onClick = { navController.navigate(navItem.path) }) {
                                            Icon(
                                                navItem.icon,
                                                contentDescription = null,
                                                tint = if (selected) Color.White else LocalContentColor.current
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(navController, startDestination = "/", Modifier.fillMaxHeight()) {
                            composable("/") {
                                Books(Modifier.padding(innerPadding)) { bookId ->
                                    navController.navigate("/book/$bookId")
                                }
                            }
                            composable("/about-store") {
                                AboutStore(Modifier.padding(innerPadding))
                            }
                            composable("/about-me") {
                                AboutMe(Modifier.padding(innerPadding))
                            }
                            composable(
                                "/book/{id}",
                                arguments = listOf(navArgument("id") { type = NavType.IntType })
                            ) { navBackStackEntry ->
                                navBackStackEntry.arguments?.let { args ->
                                    Book(args.getInt("id"), Modifier.padding(innerPadding))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}