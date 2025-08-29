package com.wintercloud.room.service

import com.wintercloud.room.entity.Room
import com.wintercloud.room.entity.RoomUser
import com.wintercloud.room.repository.RoomRepository
import com.wintercloud.room.repository.RoomUserRepository
import com.wintercloud.room.utils.UUIDv7
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val roomUserRepository: RoomUserRepository,
) {
    @Transactional(readOnly = true)
    suspend fun getRoomList(
        userId: UUID,
    ): List<Room> {
        return roomUserRepository.findAllByUserId(userId)
            // 사라진 방의 경우 제외하고 진행
            .mapNotNull { roomRepository.findById(it.roomId) }
            .toList()
    }

    @Transactional
    suspend fun createRoom(
        roomName: String,
        createdBy: UUID,
    ) {
        // UUID 확인하기 -> User Service 요청
        val savedRoom = roomRepository.save(
            entity = Room(
                id = UUIDv7.randomUUID(),
                destination = UUIDv7.randomUUID(),
                name = roomName,
                createdBy = createdBy,
            )
        )

        roomUserRepository.save(
            entity = RoomUser(
                id = UUIDv7.randomUUID(),
                roomId = savedRoom.id,
                userId = createdBy,
            )
        )
    }
}