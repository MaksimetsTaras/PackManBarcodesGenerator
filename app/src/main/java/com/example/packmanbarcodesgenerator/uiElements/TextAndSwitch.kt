import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextAndSwitch(text: String, modifier: Modifier, element: MutableState<Boolean>) {

    Row(
        modifier = modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 30.sp)
        Switch(
            checked = element.value,
            onCheckedChange = { element.value = !element.value },
            thumbContent = {
                Icon(
                    imageVector = if (element.value) Icons.Filled.Add else Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedIconColor = Color.DarkGray,
                uncheckedThumbColor = Color.Red,
                uncheckedIconColor = Color.LightGray,
                disabledCheckedThumbColor = Color.Green.copy(alpha = ContentAlpha.disabled),
                disabledUncheckedThumbColor = Color.Red.copy(alpha = ContentAlpha.disabled),
            )
        )
    }
}


