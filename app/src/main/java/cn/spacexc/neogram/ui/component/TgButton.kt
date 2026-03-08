package cn.spacexc.telegram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.settings.settingsDataStore
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.theme.parseColor

@Composable
fun TgButton(modifier: Modifier = Modifier, text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickVfx(onClick = onClick)
            .fillMaxWidth()
            .background(parseColor("#67A4ED"), RoundedCornerShape(30))
            .padding(vertical = 12.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontFamily = miSans, fontWeight = FontWeight.Medium, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = icon, contentDescription = null, tint = Color.White)
    }
}

@Preview(device = Devices.WEAR_OS_SQUARE)
@Composable
private fun TgButtonPrev() {
    val context = LocalContext.current
    val settings by context.settingsDataStore.data.collectAsState(null)
    TgButton(text = "${settings?.debug}", icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight) {

    }
}