package android.template.data.network.Repository

import android.template.common.DataResource
import android.template.data.network.FileManagerApi
import android.template.data.network.model.toDomain
import android.template.domain.models.FileDomain
import javax.inject.Inject

interface FileManagerRepository {
    suspend fun getFiles(): DataResource<List<FileDomain>>
}

class FileManagerRepositoryImpl @Inject constructor(
    private val api: FileManagerApi
) : FileManagerRepository {
    override suspend fun getFiles(): DataResource<List<FileDomain>> {
        return try {
            DataResource.Success(
                data = api.getAllFiles().map { it.toDomain() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            DataResource.Error(
                message = e.stackTraceToString()
            )
        }
    }
}