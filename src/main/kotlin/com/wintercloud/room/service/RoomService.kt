package com.wintercloud.room.service

import com.wintercloud.room.entity.Room
import com.wintercloud.room.repository.RoomRepository
import com.wintercloud.room.utils.UUIDv7
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class RoomService(
    private val roomRepository: RoomRepository,
) {
    @Transactional
    suspend fun createRoom(
        roomName: String,
        createdBy: UUID,
    ) {
        // UUID 확인하기 -> User Service 요청
        roomRepository.save(
            entity = Room(
                id = UUIDv7.randomUUID(),
                destination = UUIDv7.randomUUID(),
                name = roomName,
                createdBy = createdBy,
            )
        )
    }
}