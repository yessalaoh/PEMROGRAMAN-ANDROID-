package id.app.ddwancan.data.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val id: Int,
    val title: String,
    val summary: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("news_site")
    val newsSite: String,
    @SerializedName("published_at")
    val publishedAt: String
)
