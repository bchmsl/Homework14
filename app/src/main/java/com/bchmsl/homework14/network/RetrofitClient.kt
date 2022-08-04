package com.bchmsl.homework14.network

import com.bchmsl.homework14.model.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitClient {
    companion object{
        private const val ENDPOINT = "v3/f4864c66-ee04-4e7f-88a2-2fbd912ca5ab"
    }

    @GET(ENDPOINT)
    suspend fun getPosts(): Response<PostsResponse>
}