package com.theolin.filemanagerapplication.Data.Database

import android.template.domain.models.FileDomain
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query


@Entity
data class FileStore(
    val name: String,
    val type: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

fun FileDomain.toStore(): FileStore = FileStore(
    name = this.name,
    type = this.type
)

fun FileStore.toDomain(): FileDomain = FileDomain(
    name = this.name,
    type = this.type
)


@Dao
interface FileManagerDb {
    @Insert
    fun insertAll(vararg users: FileStore)

    @Query("SELECT * FROM filestore")
    fun getAll(): List<FileStore>

    @Query("SELECT * FROM filestore WHERE uid = :id")
    suspend fun getFileStoreById(id: Int): FileStore

    @Query("DELETE FROM filestore WHERE type = :path")
    suspend fun deleteByPath(path: String)

    @Query("DELETE FROM filestore")
    suspend fun deleteAll()
}