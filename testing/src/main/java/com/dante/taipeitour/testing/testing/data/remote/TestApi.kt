package com.dante.taipeitour.testing.testing.data.remote

import com.dante.taipeitour.data.remote.TaipeiTourApi
import com.dante.taipeitour.model.AttractionResponse

class TestApi : TaipeiTourApi {
    override suspend fun getAttractions(language: String, page: Int): AttractionResponse {
        return AttractionResponse(total = 3, data = attractionsTestData)
    }
}
