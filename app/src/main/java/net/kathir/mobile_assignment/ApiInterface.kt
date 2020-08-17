package net.kathir.mobile_assignment

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/mobile_exercise")
    fun sendInfo(@Body appInfoModel: AppInfoModel): Call<Void>

}