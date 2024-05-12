package android.template.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FileCardComponent(
    title: String,
    text: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            Modifier.padding(2.dp)
        ){
            Column {
                Text(
                    modifier = Modifier.padding(start = 6.dp, top = 4.dp),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = Modifier.padding(
                        top = 2.dp,
                        start = 6.dp,
                        bottom = 4.dp,
                        end = 4.dp
                    ),
                    text = text,
                    style = MaterialTheme.typography.bodyMedium ,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFileCardComponent() {
    FileCardComponent(title = "Title", text = "Body Text")
}
