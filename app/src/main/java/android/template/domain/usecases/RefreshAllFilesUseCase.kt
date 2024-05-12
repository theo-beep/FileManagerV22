package android.template.domain.usecases

import android.template.common.DataResource
import android.template.data.Repository.FileManagerRepository
import android.template.domain.models.FileDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RefreshAllFilesUseCase @Inject constructor(
    private val fileRepository: FileManagerRepository
) {

    operator fun invoke(): Flow<DataResource<List<FileDomain>>> = flow {
        try {
            emit(DataResource.Loading())
            when (val result = fileRepository.refreshDb()) {
                is DataResource.Success -> {
                    emit(DataResource.Success(result.data ?: emptyList()))
                }

                is DataResource.Error -> emit(DataResource.Error(result.message))
                is DataResource.Loading -> emit(DataResource.Loading())
            }

        } catch (e: HttpException) {
            emit(DataResource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(DataResource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}