package com.wintercloud.room.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID
import kotlin.jvm.javaClass

@Table
class RoomUser(

    // NOTE: R2DBC 복합키 미지원으로 인해 임시 PK 생성
    //          Spring Boot 4.0 이후 부터 지원한다고 함. (정식 버전 출시 할 시 마이그레이션 하면서 변경 예정)
    // @Id
    // private val id: RoomUserId,
    @Id
    private val id: UUID,

    val roomId: UUID,
    val userId: UUID,

    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
) : Persistable<UUID> {

    @field:Transient
    private var isNew: Boolean = true

    override fun getId(): UUID = this.id
    override fun isNew(): Boolean = this.isNew
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RoomUser
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}