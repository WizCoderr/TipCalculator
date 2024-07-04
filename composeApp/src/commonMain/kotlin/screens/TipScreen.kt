package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import components.TipButton
import components.TipEditText
import org.jetbrains.compose.resources.painterResource
import tipclc.composeapp.generated.resources.Res
import tipclc.composeapp.generated.resources.ic_add
import tipclc.composeapp.generated.resources.ic_remove
import utills.calculateTip
import utills.calculateTotalTip

@Composable
fun TipScreen(modifier: Modifier = Modifier) {

    Surface {
        Column {
           BillForm()
        }
    }
}


@Composable
fun TipTopSection(
    modifier: Modifier = Modifier,
    tipAmount:Double = 0.0
){
    Surface(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(150.dp)
    ){
        Column(
            modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color= MaterialTheme.colors.primary.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text="Total Per Person...", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onBackground)
            Text(text="₹$tipAmount", style = MaterialTheme.typography.h3, color = MaterialTheme.colors.onBackground)
        }
    }
}



@Composable
fun BillForm(modifier: Modifier = Modifier,onValChange: (String) -> Unit = {}) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val person = remember {
        mutableStateOf(1)
    }
    val sliderPossession = remember {
        mutableStateOf(0f)
    }
    val tipPercentage = (sliderPossession.value * 100).toInt()
    val tipAmountState = remember {
        mutableStateOf(0.0)
    }
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    TipTopSection(modifier = modifier, tipAmount = totalPerPerson.value)
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.background.copy(alpha = 0.6F))
    ){
        Column(
            modifier = modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            TipEditText(
                modifier = modifier.fillMaxWidth(),
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            if(validState){
                Row(
                    modifier = modifier.padding(3.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Split...", modifier = modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(100.dp))
                    Row(
                        modifier = modifier.padding(horizontal = 3.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        TipButton(
                            painter = painterResource(Res.drawable.ic_remove),
                            onClick = {
                                person.value = (if (person.value > 1) person.value - 1 else 1)
                            }
                        )
                        Text(text = person.value.toString(), modifier = modifier
                            .padding(vertical = 9.dp)
                            .align(Alignment.CenterVertically))
                        TipButton(
                            painter = painterResource(Res.drawable.ic_add),
                            onClick = {
                                person.value = (person.value+ 1)
                            }
                        )
                    }
                }

                // Top Row
                Row(
                    modifier = modifier
                        .padding(horizontal = 3.dp, vertical = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Tip",
                        modifier = modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier.width(200.dp))
                    Text(
                        text = "₹${tipAmountState.value}",
                        modifier = modifier.align(Alignment.CenterVertically)
                    )
                }
                Column(
                    modifier = modifier
                        .padding(horizontal = 3.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text="$tipPercentage%",modifier = modifier.align(Alignment.CenterHorizontally))
                    Spacer(modifier = modifier.height(13.dp))
                    Slider(
                        value = sliderPossession.value,
                        onValueChange ={
                            sliderPossession.value = it
                            tipAmountState.value = calculateTip(totalBillState.value.toDouble(),tipPercentage)
                            totalPerPerson.value = calculateTotalTip(totalBillState.value.toDouble(),tipPercentage,person.value)
                        },
                        modifier = modifier.padding(start = 6.dp,end= 6.dp ),
                        steps = 5,
                        onValueChangeFinished = {

                        },
                    )
                }
            }else Box{}
        }
    }
}

