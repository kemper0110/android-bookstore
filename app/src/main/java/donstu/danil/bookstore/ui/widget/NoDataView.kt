package donstu.danil.bookstore.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.volley.VolleyError

@Composable
fun NoDataView(error: VolleyError?, modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxWidth(),
        Alignment.Center
    ) {
        if (error != null)
            return Text(text = error.message.toString())
        CircularProgressIndicator(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 3.dp),
        )
    }
}