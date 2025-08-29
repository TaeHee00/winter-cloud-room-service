package com.wintercloud.room.controller

import com.wintercloud.room.auth.LoginUser
import com.wintercloud.room.dto.CreateRoomRequest
import com.wintercloud.room.entity.Room
import com.wintercloud.room.service.RoomService
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
//@RequestMapping("/rooms")
class RoomController(
    private val roomService: RoomService,
) {

    @GetMapping
    suspend fun getRoomList(
        @LoginUser userId: UUID,
    ): ResponseEntity<List<Room>> {
        return ResponseEntity.ok(roomService.getRoomList(userId))
    }

    @PostMapping
    suspend fun createRoom(
        @LoginUser userId: UUID,
        @RequestBody request: CreateRoomRequest,
    ) {
        roomService.createRoom(
            roomName = request.roomName,
            createdBy = userId,
        )
    }
}