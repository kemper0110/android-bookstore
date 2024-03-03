package donstu.danil.bookstore.rest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import donstu.danil.bookstore.LocalRequestQueue
import donstu.danil.bookstore.R
import kotlinx.serialization.json.Json

@Composable
inline fun <reified T> rememberQuery(url: String): Pair<T?, VolleyError?> {
    val context = LocalContext.current
    val queue = LocalRequestQueue.current

    var error by remember(url) { mutableStateOf<VolleyError?>(null) }
    var data by remember(url) { mutableStateOf<T?>(null) }

    LaunchedEffect(url) {
        val stringRequest = object : StringRequest(
            Method.GET, context.getString(R.string.api_url) + url,
            { data = Json.decodeFromString<T>(it) },
            { error = it }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return hashMapOf("Accept" to "application/json")
            }
        }
        queue.add(stringRequest)
    }
    return Pair(data, error)
}