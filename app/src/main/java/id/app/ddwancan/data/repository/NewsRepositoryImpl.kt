package id.app.ddwancan.data.repository

import com.example.bebas.data.domain.model.Article
import com.example.bebas.data.remote.NewsApiService

class NewsRepositoryImpl(
    private val api: NewsApiService
) : NewsRepository {

    override suspend fun getNews(): List<Article> {
        return api.getNews().results.map {
            Article(
                title = it.title,
                description = it.summary,
                publishedAt = it.publishedAt
            )
        }
    }
}
