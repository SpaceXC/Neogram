package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.ui.theme.InputBarGray

@Composable
fun NeoIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconModifier: Modifier = Modifier,
    size: Dp = 24.dp
) {
    Box(modifier = modifier.background(InputBarGray, RoundedCornerShape(10.dp))) {
        Icon(
            icon,
            null,
            modifier = iconModifier
                .padding(vertical = 6.dp)
                .size(size)
                .align(Alignment.Center),
            tint = Color.White
        )
    }
}