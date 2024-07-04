package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import tipclc.composeapp.generated.resources.Res
import tipclc.composeapp.generated.resources.ic_attach_money

@Composable
fun TipEditText(
    modifier: Modifier = Modifier,
    valueState:MutableState<String>,
    labelId: String,
    enabled:Boolean,
    isSingleLine: Boolean,
    keyboardType:KeyboardType= KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = {
            Text(text=labelId)
        },
         enabled= enabled,
        singleLine = isSingleLine,
        leadingIcon = {
            Icon(painter = painterResource(Res.drawable.ic_attach_money) , contentDescription = "Money Icon")
        },
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction
    )
}
val IconButtonSizeModifier = Modifier.size(40.dp)

@Composable
fun TipButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    tint: Color=Color.Black.copy(alpha = 0.5f),
    background :Color=MaterialTheme.colors.background,
    elevation:Dp=4.dp

) {
    Card(
        modifier = modifier.padding(4.dp)
            .clickable { onClick.invoke() }.then(IconButtonSizeModifier),
        shape = CircleShape,
        elevation = elevation,
        backgroundColor = background
    ) {
        Icon(
            painter = painter,
            contentDescription = "Plus or minus icon",
            tint = tint,
            modifier = modifier.padding(10.dp)
        )
    }
}