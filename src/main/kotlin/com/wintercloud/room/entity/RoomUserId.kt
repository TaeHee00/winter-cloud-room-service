package com.wintercloud.room.entity

import java.util.UUID

data class RoomUserId(
    val roomId: UUID,
    val userId: UUID,
)
