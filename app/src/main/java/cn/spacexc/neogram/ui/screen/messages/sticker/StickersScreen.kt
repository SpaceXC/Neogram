package cn.spacexc.neogram.ui.screen.messages.sticker

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.sticker.StickerRepository
import cn.spacexc.neogram.ui.component.TgSticker
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data object StickersScreen

@Parcelize
data class StickerMessage(
    val fileId: Int,
    val emoji: String,
    val width: Int,
    val height: Int
): Parcelable

@Composable
fun StickersScreen(
    navController: NavController
) {
    TitleFrame(
        "贴纸",
        onTitleClicked = {},
        onActionClicked = navController::navigateUp
    ) { topPadding ->
        val stickerSets by StickerRepository.stickerSetsInfo.collectAsState()
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState { stickerSets.size }
        val stickerSetLazyRowState = rememberLazyListState()
        LaunchedEffect(pagerState.currentPage) {
            scope.launch { stickerSetLazyRowState.animateScrollToItem(pagerState.currentPage) }
        }
        Column(
            modifier = Modifier
                .padding(top = topPadding)
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                state = stickerSetLazyRowState,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                stickerSets.forEachIndexed { index, stickerSet ->
                    item {
                        stickerSet.covers.first()?.let { sticker ->
                            TgSticker(
                                sticker = sticker,
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        if (pagerState.currentPage == index) Color.White.copy(alpha = 0.3f) else Color.Transparent,
                                        RoundedCornerShape(3.dp)
                                    )
                                    .padding(3.dp)
                                    .clickVfx {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                            )
                        }
                    }
                }
            }
            HorizontalPager(pagerState) { page ->
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    var stickerSet by remember { mutableStateOf<TdApi.StickerSet?>(null) }
                    Text(
                        stickerSets[page].title,
                        color = Color.White,
                        fontFamily = miSans
                    )
                    LaunchedEffect(Unit) {
                        TdClient.send(TdApi.GetStickerSet(stickerSets[page].id), { stickers ->
                            if (stickers is TdApi.StickerSet) {
                                stickerSet = stickers
                            }
                        })
                    }
                    if (stickerSet == null) {
                        Text(
                            "Loading...",
                            color = Color.White,
                            fontFamily = miSans
                        )
                    }
                    stickerSet?.let { stickers ->
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            contentPadding = PaddingValues(bottom = 8.dp)
                        ) {
                            stickers.stickers.forEach { sticker ->
                                item {
                                    TgSticker(
                                        sticker = sticker,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f)
                                            .clickVfx {
                                                navController.previousBackStackEntry
                                                    ?.savedStateHandle
                                                    ?.set("stickerToSend", StickerMessage(
                                                        fileId = sticker.sticker.id,
                                                        width = sticker.width,
                                                        height = sticker.height,
                                                        emoji = sticker.emoji
                                                    ))
                                                navController.navigateUp()
                                            })
                                    Text(
                                        sticker.emoji,
                                        color = Color.White,
                                        fontFamily = miSans
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}