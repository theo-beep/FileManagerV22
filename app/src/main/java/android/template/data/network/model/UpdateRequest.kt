package android.template.data.network.model


import com.google.gson.annotations.SerializedName

data class UpdateRequest(
    @SerializedName("isDirectory")
    val isDirectory: Boolean,
    @SerializedName("newPath")
    val newPath: String,
    @SerializedName("oldPath")
    val oldPath: String
)