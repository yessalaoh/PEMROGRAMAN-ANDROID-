package id.app.ddwancan.data.repository

import com.example.bebas.data.domain.model.Article

interface NewsRepository {
    suspend fun getNews(): List<Article>
}
