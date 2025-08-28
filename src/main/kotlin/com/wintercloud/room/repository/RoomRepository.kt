package com.wintercloud.room.repository

import com.wintercloud.room.entity.Room
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoomRepository: CoroutineCrudRepository<Room, UUID> {
}