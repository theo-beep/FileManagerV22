package android.template.data.network

import android.template.data.network.model.CreateFileRequest
import android.template.data.network.model.GetFilesResponse
import android.template.data.network.model.UpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FileManagerApi {

    @GET("/files?path=")
    suspend fun getAllFiles() : GetFilesResponse

    @GET("/files")
    suspend fun getAllFilesInAFolder(
        @Query("path") path: String,
    ) : GetFilesResponse

    @POST("documents/new_folder")
    suspend fun postNewDocument(@Body requestBody: CreateFileRequest): Response<Any>

    @PUT("/files")
    suspend fun putFilePath(@Body requestBody: UpdateRequest)

    @DELETE("/files")
    suspend fun deleteFilePath(@Body path: String)
}