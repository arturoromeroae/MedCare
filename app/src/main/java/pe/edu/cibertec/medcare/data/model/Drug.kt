package pe.edu.cibertec.medcare.data.model

import com.google.gson.annotations.SerializedName

data class Drug(
    val id: Int,
    val name: String,
    val concent: String,
    val type: String,
    val presentation: String,
    @SerializedName("image")
    val image: String
)
