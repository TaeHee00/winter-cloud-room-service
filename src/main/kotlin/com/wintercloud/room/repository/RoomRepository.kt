package com.wintercloud.room.repository

import com.wintercloud.message.entity.Room
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoomRepository: CoroutineCrudRepository<Room, UUID> {
}