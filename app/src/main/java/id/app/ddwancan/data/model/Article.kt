//package id.app.ddwancan.data.model
//
//data class Article(
//    val id: Int,
//    val title: String,
//    val summary: String,
//    val imageUrl: String,
//    val newsSite: String,
//    val publishedAt: String
//)
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
