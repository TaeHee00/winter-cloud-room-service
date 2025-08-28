package com.wintercloud.room.service

import com.wintercloud.message.entity.Room
import com.wintercloud.message.repository.RoomRepository
import com.wintercloud.message.utils.UUIDv7
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