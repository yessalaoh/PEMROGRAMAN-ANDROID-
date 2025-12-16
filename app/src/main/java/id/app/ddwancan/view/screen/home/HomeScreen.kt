package id.app.ddwancan.view.screen.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
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
=======

import id.app.ddwancan.R

import id.app.ddwancan.data.model.Berita
import id.app.ddwancan.view.activity.CategoryActivity
import id.app.ddwancan.view.activity.FavoriteActivity
import id.app.ddwancan.view.activity.ProfileActivity
import id.app.ddwancan.view.activity.SearchActivity
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt

/* ============================================================
   DUMMY DATA
============================================================ */
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
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
=======
private val dummyBerita = listOf(
    Berita(1, "Universitas dan Organisasi Turki", "UKDW Perluas Jejaring...", "news", 20, "3 Hours ago"),
    Berita(2, "Dies Natalis UKDW", "Fun Run meriah penuh semangat!", "news", 55, "7 Hours ago"),
    Berita(3, "Duta Voice Raih Emas", "Prestasi International Bandung Festival", "news", 150, "12 Hours ago")
)

/* ============================================================
   HOME SCREEN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { HomeBottomBar(context) },
        containerColor = Color.White
    ) { padding ->
        HomeContent(
            modifier = Modifier.padding(padding),
            onCategoryClick = { category ->
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra("CATEGORY", category)
                context.startActivity(intent)
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakingNewsImage() {
    Image(
        painter = painterResource(R.drawable.logo2),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(Color.LightGray, RoundedCornerShape(12.dp))
    )
}
/* ============================================================
   TOP BAR (LOGO TENGAH)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.logo1),
                contentDescription = "D'Wacana Logo",
                modifier = Modifier.height(48.dp),
                contentScale = ContentScale.Fit
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

/* ============================================================
   CONTENT
============================================================ */
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        item {
            Spacer(Modifier.height(16.dp))
            CategoryChips(onCategoryClick)
            Spacer(Modifier.height(24.dp))
            Text("News Today", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            BreakingNewsImage()
            Spacer(Modifier.height(24.dp))
        }

        items(dummyBerita) { berita ->
            NewsCard(berita)
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}

/* ============================================================
   CATEGORY CHIPS
============================================================ */
@Composable
fun CategoryChips(onCategoryClick: (String) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item { ChipItem("All", true) { onCategoryClick("all") } }
        item { ChipItem("Teknologi") { onCategoryClick("teknologi") } }
        item { ChipItem("Edukasi") { onCategoryClick("edukasi") } }
        item { ChipItem("Sosial") { onCategoryClick("sosial") } }
        item { ChipItem("Kesehatan") { onCategoryClick("kesehatan") } }
        item { ChipItem("Olahraga") { onCategoryClick("olahraga") } }
        item { ChipItem("Seni") { onCategoryClick("seni") } }
    }
}

@Composable
fun ChipItem(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) Color(0xFF2678FF) else Color(0xFFEFF3FF),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (selected) Color.White else Color(0xFF2678FF),
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
        )
    }
}

/* ============================================================
   NEWS CARD
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
============================================================ */
@Composable
fun NewsCard(berita: Berita) {
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
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
                painter = painterResource(id = item.image),
=======
                painter = painterResource(R.drawable.news),
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
                Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(item.desc, maxLines = 2, fontSize = 13.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("👁 ${item.views}", fontSize = 12.sp)
=======
                Text(berita.judul, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(berita.deskripsi, maxLines = 2, fontSize = 13.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("👁 ${berita.views}", fontSize = 12.sp)
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
                    Spacer(Modifier.width(12.dp))
                    Text(berita.createdAt, fontSize = 12.sp)
                }
            }
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
=======

            IconButton(onClick = {}) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
            }
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
        }
    }
}

/* ============================================================
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
   UI PART 
=======
   BOTTOM NAVIGATION
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD:app/src/main/java/id/app/ddwancan/view/HomeActivity.kt
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
=======
fun HomeBottomBar(context: android.content.Context) {
    Column {
        HorizontalDivider(color = Color(0xFFE0E0E0))

        NavigationBar(containerColor = Color.White) {

            NavItem(Icons.Default.Home, "Home", true) {}

            NavItem(Icons.Default.Search, "Search") {
                context.startActivity(Intent(context, SearchActivity::class.java))
            }

            NavItem(Icons.Default.Favorite, "Favorite") {
                context.startActivity(Intent(context, FavoriteActivity::class.java))
            }

            NavItem(Icons.Default.Person, "Profile") {
                context.startActivity(Intent(context, ProfileActivity::class.java))
            }
        }
    }
}

@Composable
fun RowScope.NavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label) }
    )
}
>>>>>>> ba3eb48cc14f2c05cbc22fe1afd12b85475a0106:app/src/main/java/id/app/ddwancan/view/screen/home/HomeScreen.kt
