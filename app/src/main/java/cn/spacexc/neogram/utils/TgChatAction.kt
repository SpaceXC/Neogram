package cn.spacexc.neogram.utils

import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import org.drinkless.tdlib.TdApi

fun ChatListRepository.ChatAction?.getChatActionDescription(users: Map<Long, TdApi.User>, chats: Map<Long, TdApi.Chat>, chatType: TdApi.ChatType?): String? {
    return if (this != null && this.action !is TdApi.ChatActionCancel) {
        /**
         * ChatActionTyping.CONSTRUCTOR,
         * ChatActionRecordingVideo.CONSTRUCTOR,
         * ChatActionUploadingVideo.CONSTRUCTOR,
         * ChatActionRecordingVoiceNote.CONSTRUCTOR,
         * ChatActionUploadingVoiceNote.CONSTRUCTOR,
         * ChatActionUploadingPhoto.CONSTRUCTOR,
         * ChatActionUploadingDocument.CONSTRUCTOR,
         * ChatActionChoosingSticker.CONSTRUCTOR,
         * ChatActionChoosingLocation.CONSTRUCTOR,
         * ChatActionChoosingContact.CONSTRUCTOR,
         * ChatActionStartPlayingGame.CONSTRUCTOR,
         * ChatActionRecordingVideoNote.CONSTRUCTOR,
         * ChatActionUploadingVideoNote.CONSTRUCTOR,
         * ChatActionWatchingAnimations.CONSTRUCTOR,
         */
        val actionSenderName = if (senderId is TdApi.MessageSenderUser) {
            val actionUser = users[senderId.userId]
            actionUser?.firstName ?: ""
        } else {
            val actionChat = chats[(senderId as TdApi.MessageSenderChat).chatId]
            actionChat?.title
        }
        val actionName = when (action) {
            is TdApi.ChatActionTyping -> "正输入"
            is TdApi.ChatActionRecordingVideo -> "正录制视频"
            is TdApi.ChatActionUploadingVideo -> "正上传视频"
            is TdApi.ChatActionRecordingVoiceNote -> "正录制语音"
            is TdApi.ChatActionUploadingVoiceNote -> "正上传语音"
            is TdApi.ChatActionUploadingPhoto -> "正上传照片"
            is TdApi.ChatActionUploadingDocument -> "正上传文件"
            is TdApi.ChatActionChoosingSticker -> "正挑选贴纸"
            is TdApi.ChatActionChoosingLocation -> "正选择定位"
            is TdApi.ChatActionRecordingVideoNote -> "正录制视频"
            is TdApi.ChatActionUploadingVideoNote -> "正上传视频"
            else -> null
        }
        if (actionSenderName.isNullOrEmpty() || chatType is TdApi.ChatTypePrivate) actionName else "$actionSenderName${actionName?.lowercase()}"
    } else {
        if (chatType is TdApi.ChatTypePrivate) {
            val userId = chatType.userId
            val currentUser = users[userId]
            val status = currentUser?.status
            /**
             * UserStatusEmpty.CONSTRUCTOR,
             * UserStatusOnline.CONSTRUCTOR,
             * UserStatusOffline.CONSTRUCTOR,
             * UserStatusRecently.CONSTRUCTOR,
             * UserStatusLastWeek.CONSTRUCTOR,
             * UserStatusLastMonth.CONSTRUCTOR
             */
            when (status) {
                is TdApi.UserStatusOnline -> "在线"
                is TdApi.UserStatusOffline -> "${formatTimestamp(status.wasOnline.toLong())}在线"
                is TdApi.UserStatusRecently -> "刚才在线"
                is TdApi.UserStatusLastWeek -> "上周曾在线"
                is TdApi.UserStatusLastMonth -> "上个月曾在线"
                else -> null
            }
        } else null
    }
}