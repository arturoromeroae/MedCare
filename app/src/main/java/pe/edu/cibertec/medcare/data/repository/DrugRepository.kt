package pe.edu.cibertec.medcare.data.repository

import pe.edu.cibertec.medcare.data.model.Drug
import pe.edu.cibertec.medcare.data.remote.ApiClient
import pe.edu.cibertec.medcare.data.remote.service.DrugService
import pe.edu.cibertec.medcare.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrugRepository(
    private val drugService: DrugService = ApiClient.getDrugInterface()

) {
    fun getDrugs(callback: (Result<List<Drug>>) -> Unit) {

        drugService.getDrugs().enqueue(object : Callback<List<Drug>> {
            override fun onResponse(
                call: Call<List<Drug>>,
                response: Response<List<Drug>>
            ) {
                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }
                callback(Result.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<Drug>>, t: Throwable) {
                callback(Result.Error("Get drugs not available"))
            }
        })
    }

    fun createDrugs(
        id: Int,
        name: String,
        concent: String,
        type: String,
        presentation: String,
        image: String,
        callback: (Result<Boolean>) -> Unit
    ) {
        drugService.createDrug(Drug(id, name, concent, type, presentation, image))
            .enqueue(object : Callback<Drug> {
                override fun onResponse(call: Call<Drug>, response: Response<Drug>) {
                    if (!response.isSuccessful) {
                        callback(Result.Error("Unsuccessful response"))
                        return
                    }

                    if (response.body() == null) {
                        callback(Result.Error("No data found"))
                        return
                    }
                    callback(Result.Success(true))

                }

                override fun onFailure(call: Call<Drug>, t: Throwable) {
                    callback(Result.Error("Create drug not available"))
                }
            })
    }

    fun deleteDrug(id: Int, callback: (Result<Boolean>) -> Unit) {
        drugService.deleteDrug(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                callback(Result.Success(true))
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback(Result.Error("Delete drug not available"))
            }
        })
    }

    fun updateDrug(drug: Drug, callback: (Result<Boolean>) -> Unit) {
        drugService.updateDrug(drug.id, drug).enqueue(object : Callback<Drug> {
            override fun onResponse(call: Call<Drug>, response: Response<Drug>) {
                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }
                callback(Result.Success(true))
            }

            override fun onFailure(call: Call<Drug>, t: Throwable) {
                callback(Result.Error("Update drug not available"))
            }
        })
    }
}