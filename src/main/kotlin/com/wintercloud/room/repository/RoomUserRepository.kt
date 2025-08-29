package com.wintercloud.room.repository

import com.wintercloud.room.entity.RoomUser
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoomUserRepository: CoroutineCrudRepository<RoomUser, UUID> {
    suspend fun findAllByUserId(userId: UUID): List<RoomUser>
}