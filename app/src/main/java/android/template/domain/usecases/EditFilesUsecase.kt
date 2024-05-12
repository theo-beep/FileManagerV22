package android.template.domain.usecases

import android.template.data.Repository.FileManagerRepository
import javax.inject.Inject

class EditFilesUsecase @Inject constructor(
    private val fileRepository: FileManagerRepository
) {

}