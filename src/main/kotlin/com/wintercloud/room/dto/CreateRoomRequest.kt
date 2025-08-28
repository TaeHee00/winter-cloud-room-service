package com.wintercloud.room.dto

import java.util.UUID

data class CreateRoomRequest(
    val roomName: String,
    val createdBy: UUID,
)
