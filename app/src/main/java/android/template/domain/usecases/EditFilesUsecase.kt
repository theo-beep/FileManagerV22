package android.template.domain.usecases

import android.template.common.DataResource
import android.template.data.Repository.FileManagerRepository
import android.template.domain.models.FileDomain
import com.theolin.filemanagerapplication.Data.Database.FileStore
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EditFilesUsecase @Inject constructor(
    private val fileRepository: FileManagerRepository
) {
    suspend operator fun invoke(file: FileDomain) = flow {
        try {
            emit(DataResource.Loading())
            when (val result = fileRepository.editFile(file)) {
                is DataResource.Success -> {
                    emit(DataResource.Success(result.data))
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