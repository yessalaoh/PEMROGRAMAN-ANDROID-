package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import id.app.ddwancan.R
import id.app.ddwancan.data.repository.NewsRepository
import id.app.ddwancan.data.repository.NewsRepositoryImpl
import id.app.ddwancan.data.remote.RetrofitInstance
import id.app.ddwancan.ui.theme.DDwancanTheme
import kotlinx.coroutines.launch

/* ============================================================
   ACTIVITY
============================================================ */
class HomeActivity : ComponentActivity() {

    private val repository by lazy {
        NewsRepositoryImpl(RetrofitInstance.api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DDwancanTheme {
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(repository)
                )
                HomeScreen(viewModel)
            }
        }
    }
}

/* ============================================================
   UI MODEL 
============================================================ */
data class NewsItem(
    val title: String,
    val desc: String,
    val image: Int,
    val views: Int,
    val time: String
)

/* ============================================================
   VIEWMODEL (API INTEGRATED)
============================================================ */
class HomeViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    var newsList by mutableStateOf<List<NewsItem>>(emptyList())
        private set

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val articles = repository.getNews()
                newsList = articles.map {
                    NewsItem(
                        title = it.title,
                        desc = it.summary,
                        image = R.mipmap.ic_launcher,
                        views = 0,
                        time = it.publishedAt
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

/* ============================================================
   VIEWMODEL FACTORY
============================================================ */
class HomeViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/* ============================================================
   HOME SCREEN 
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            news = viewModel.newsList
        )
    }
}

/* ============================================================
   MAIN CONTENT
============================================================ */
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    news: List<NewsItem>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        item {
            Spacer(Modifier.height(16.dp))
            Text("Breaking News", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            BreakingNewsImage()
            Spacer(Modifier.height(12.dp))
            CategoryChips()
            Spacer(Modifier.height(16.dp))
            Text("News Today", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
        }

        items(news) { item ->
            NewsCard(item)
        }
    }
}

/* ============================================================
   NEWS CARD 
============================================================ */
@Composable
fun NewsCard(item: NewsItem) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(item.desc, maxLines = 2, fontSize = 13.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("👁 ${item.views}", fontSize = 12.sp)
                    Spacer(Modifier.width(12.dp))
                    Text(item.time, fontSize = 12.sp)
                }
            }
        }
    }
}

/* ============================================================
   UI PART 
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "DDwancan", fontWeight = FontWeight.Bold) },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        }
    )
}

@Composable
fun BreakingNewsImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.LightGray, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().padding(12.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips() {
    val categories = listOf("All", "Tech", "Science", "Society", "Education")
    var selected by remember { mutableStateOf(0) }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        categories.forEachIndexed { i, c ->
            FilterChip(
                selected = selected == i,
                onClick = { selected = i },
                label = { Text(c) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
    var selectedIndex by remember { mutableStateOf(0) }
    NavigationBar {
        NavigationBarItem(selectedIndex == 0, { selectedIndex = 0 }, { Icon(Icons.Default.Home, null) }, { Text("Home") })
        NavigationBarItem(selectedIndex == 1, { selectedIndex = 1 }, { Icon(Icons.Default.Favorite, null) }, { Text("Fav") })
        NavigationBarItem(selectedIndex == 2, { selectedIndex = 2 }, { Icon(Icons.Default.Person, null) }, { Text("Me") })
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHome() {
    DDwancanTheme { }
}
