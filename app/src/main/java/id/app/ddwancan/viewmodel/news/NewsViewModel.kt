package id.app.ddwancan.viewmodel.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.app.ddwancan.data.repository.NewsRepository
import id.app.ddwancan.domain.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            _news.value = repository.getNews()
        }
    }
}

