package com.dante.taipeitour.data.remote

import com.dante.taipeitour.model.AttractionResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TaipeiTourApi {
    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") language: String,
        @Query("page") page: Int
    ): AttractionResponse
}
