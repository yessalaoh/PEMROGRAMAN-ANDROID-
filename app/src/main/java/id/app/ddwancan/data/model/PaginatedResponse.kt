package id.app.ddwancan.data.model

data class PaginatedResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ArticleDto>
)
