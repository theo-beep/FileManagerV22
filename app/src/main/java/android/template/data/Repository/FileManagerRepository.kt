package android.template.data.Repository

import android.template.common.DataResource
import android.template.data.network.FileManagerApi
import android.template.data.network.model.CreateFileRequest
import android.template.data.network.model.toDomain
import android.template.domain.models.FileDomain
import com.theolin.filemanagerapplication.Data.Database.FileManagerDb
import com.theolin.filemanagerapplication.Data.Database.FileStore
import com.theolin.filemanagerapplication.Data.Database.toDomain
import com.theolin.filemanagerapplication.Data.Database.toStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface FileManagerRepository {
    suspend fun getFiles(): DataResource<List<FileDomain>>

    suspend fun refreshDb(): DataResource<List<FileDomain>>

    suspend fun editFile(filePath: FileDomain) :DataResource<Any>

    suspend fun deleteFile(filePath: FileDomain): DataResource<Any>

    suspend fun addNewFile(filePath: String): DataResource<List<FileDomain>>

    suspend fun clearDb()
}

class FileManagerRepositoryImpl @Inject constructor(
    private val api: FileManagerApi,
    private val db: FileManagerDb
) : FileManagerRepository {
    override suspend fun getFiles(): DataResource<List<FileDomain>> {
        //TODO : If I have time make a class to inject dispatchers for testing
        return withContext(Dispatchers.IO) {
            try {
                val result = db.getAll().map { it.toDomain() }
                DataResource.Success(
                    data = result
                )
            } catch (e: Exception) {
                e.printStackTrace()
                DataResource.Error(
                    message = e.stackTraceToString()
                )
            }
        }
    }

    override suspend fun refreshDb(): DataResource<List<FileDomain>> {
        //TODO : If I have time make a class to inject dispatchers for testing
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getAllFiles().map { it.toDomain() }
                db.deleteAll()
                result.forEach {
                    db.insertAll(it.toStore())
                }
                getFiles()
            } catch (e: Exception) {
                e.printStackTrace()
                DataResource.Error(
                    message = e.stackTraceToString()
                )
            }
        }
    }

    override suspend fun editFile(filePath: FileDomain): DataResource<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFile(filePath: FileDomain): DataResource<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewFile(filePath: String): DataResource<List<FileDomain>> {
        // TODO: If I have time make a class to inject dispatchers for testing
        return withContext(Dispatchers.IO) {
            try {
                val request = CreateFileRequest(path = filePath)
                api.postNewDocument(request)
                refreshDb()
            } catch (e: Exception) {
                DataResource.Error(message = e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun clearDb() {
        withContext(Dispatchers.IO) {
            try {
                db.deleteAll()
            } catch (e: Exception) {
                e.stackTraceToString()
            }
        }
    }
}