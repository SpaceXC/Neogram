package cn.spacexc.neogram.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.component.modifier.clickVfx

@Composable
fun PhoneNumber(viewModel: AuthViewModel) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        val textFieldState = rememberTextFieldState()
        Text(text = "输入手机号码", color = Color.White, fontFamily = miSans, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        NeoCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            background = InputBarGray,
            borderAlpha = 0.03f
        ) {
            BasicTextField(
                state = textFieldState,
                textStyle = TextStyle(fontFamily = miSans, color = Color.White),
                lineLimits = TextFieldLineLimits.SingleLine,
                cursorBrush = SolidColor(
                    NeoMain
                ),
                modifier = Modifier.padding(vertical = 14.dp, horizontal = 10.dp).fillMaxWidth()
            )
        }
        Spacer(Modifier.weight(1f))
        Text("QRCODE", color = Color.White, modifier = Modifier.clickVfx(onClick = viewModel::requestQrCode))
        NeoCard(
            modifier = Modifier.fillMaxWidth().clickVfx {
                viewModel.sendPhone(textFieldState.text.toString())
            },
            shape = RoundedCornerShape(16.dp),
            background = NeoMain
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .padding(start = 8.dp)
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "继续",
                    fontFamily = miSans,
                    //fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}