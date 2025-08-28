package com.wintercloud.room.controller

import com.wintercloud.room.dto.CreateRoomRequest
import com.wintercloud.room.service.RoomService
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomController(
    private val roomService: RoomService,
) {



    @PostMapping
    suspend fun createRoom(
        @NotNull @RequestBody request: CreateRoomRequest,
    ) {
        roomService.createRoom(
            roomName = request.roomName,
            createdBy = request.createdBy,
        )
    }
}