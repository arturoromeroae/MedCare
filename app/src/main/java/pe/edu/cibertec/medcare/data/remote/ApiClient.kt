package pe.edu.cibertec.medcare.data.remote

import pe.edu.cibertec.medcare.data.remote.service.DrugService
import pe.edu.cibertec.medcare.data.remote.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://cloud-sphenoid-clove.glitch.me/"

    private var retrofit: Retrofit? = null
    private var drugService: DrugService? = null
    private var userService: UserService? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getDrugInterface(): DrugService {
        if (drugService == null) {
            drugService = getRetrofit().create(DrugService::class.java)
        }
        return drugService as DrugService
    }

    fun getUserInterface(): UserService {
        if (userService == null) {
            userService = getRetrofit().create(UserService::class.java)
        }
        return userService as UserService
    }
}