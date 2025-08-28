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
class Room(
    @Id
    private val id: UUID,
    val name: String,
    val destination: UUID,

    val createdBy: UUID,

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
        other as Room
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}