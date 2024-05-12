package android.template.data.network.model


import com.google.gson.annotations.SerializedName
import android.template.domain.models.FileDomain

class GetFilesResponse : ArrayList<GetFilesResponse.GetFilesResponseItem>() {
    data class GetFilesResponseItem(
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String
    )
}

fun GetFilesResponse.GetFilesResponseItem.toDomain(): FileDomain = FileDomain(
    name = this.name,
    type = this.type
)

