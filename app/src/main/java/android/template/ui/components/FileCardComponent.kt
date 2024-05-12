package android.template.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Delete

@Composable
fun FileCardComponent(
    title: String,
    text: String,
    onEditClick: () -> Unit ,
    onDeleteClick : () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            Modifier.padding(2.dp)
        ){
            Column {
                Text(
                    modifier = Modifier.padding(
                        top = 2.dp,
                        start = 6.dp,
                        bottom = 0.dp,
                        end = 4.dp
                    ),
                    text = text,
                    style = MaterialTheme.typography.bodyMedium ,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, top = 2.dp, bottom = 2.dp),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            OutlinedButton(
                border = null,
                onClick = {
                onEditClick()
            }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedButton(
                border = null,
                onClick = {
                    onDeleteClick()
                }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Edit"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFileCardComponent() {
    FileCardComponent(title = "Title", text = "Body Text", onDeleteClick = {} , onEditClick = {})
}
