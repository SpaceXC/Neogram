package cn.spacexc.neogram.ui.screen.link

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.jetbrainsMono
import cn.spacexc.neogram.ui.theme.miSans
import kotlinx.serialization.Serializable

@Serializable
data class LinkProcessScreen(val link: String)

@Composable
fun LinkProcessScreen(
    navController: NavController,
    link: String,
    viewModel: LinkProcessViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.processLink(link)
    }
    TitleFrame("链接预览", onActionClicked = navController::navigateUp, onTitleClicked = {}) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp).padding(top = it, bottom = 8.dp)) {
            SelectionContainer {
                Text(link, color = Color.White, fontFamily = jetbrainsMono)
            }
            Text("COPY", color = Color.White, fontFamily = miSans, modifier = Modifier.clickable {
                val clipboard = getSystemService(context, ClipboardManager::class.java) as ClipboardManager
                val clip = ClipData.newPlainText("url", link)
                clipboard.setPrimaryClip(clip)
            })
        }
    }
}