package id.app.ddwancan.data.remote.api

import retrofit2.http.GET

interface NewsApiService {

    @GET("v4/articles")
    suspend fun getArticles(): PaginatedResponse<ArticleDto>
}
