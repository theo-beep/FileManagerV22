package android.template.data.network.model


import com.google.gson.annotations.SerializedName

data class CreateFileRequest(
    @SerializedName("path")
    val path: String
)