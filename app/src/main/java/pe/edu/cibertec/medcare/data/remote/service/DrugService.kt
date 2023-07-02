package pe.edu.cibertec.medcare.data.remote.service

import pe.edu.cibertec.medcare.data.model.Drug
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DrugService {
    @POST("drugs")
    fun createDrug(@Body drug: Drug): Call<Drug>

    @GET("drugs")
    fun getDrugs(): Call<List<Drug>>

    @DELETE("drugs/{id}")
    fun deleteDrug(@Path("id") id: Int): Call<Unit>

    @PUT("drugs/{id}")
    fun updateDrug(@Path("id") id: Int, @Body drug: Drug): Call<Drug>
}