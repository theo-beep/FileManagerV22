package android.template.data.Repository

import android.template.common.DataResource
import android.template.data.network.FileManagerApi
import android.template.data.network.model.toDomain
import android.template.domain.models.FileDomain
import com.theolin.filemanagerapplication.Data.Database.FileManagerDb
import com.theolin.filemanagerapplication.Data.Database.toDomain
import com.theolin.filemanagerapplication.Data.Database.toStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FileManagerRepository {
    suspend fun getFiles(): DataResource<List<FileDomain>>

    suspend fun refreshDb(): DataResource<List<FileDomain>>

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

    override suspend fun clearDb() {
        withContext(Dispatchers.IO) {
            try {
                db.deleteAll()
            }catch (e: Exception){
                e.stackTraceToString()
            }
        }
    }
}