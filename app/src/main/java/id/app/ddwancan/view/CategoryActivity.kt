package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.app.ddwancan.R
import id.app.ddwancan.ui.theme.DDwancanTheme

/* ============================================================
   DATA MODEL
============================================================ */
data class ArticleData(
    val id: Int,
    val title: String,
    val description: String,
    val views: Int,
    val timeAgo: String,
    val isFavorite: Boolean
)

class CategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DDwancanTheme {
                CategoryScreen()
            }
        }
    }
}

/* ============================================================
   MAIN SCREEN
============================================================ */
@Composable
fun CategoryScreen() {
    Scaffold(
        topBar = { CategoryTopBar() },
        bottomBar = { CategoryBottomBar() },
        containerColor = Color.White
    ) { innerPadding ->
        CategoryContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP BAR
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO: Back */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = "Category",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF2678FF) // Primary Blue
        )
    )
}

/* ============================================================
   CONTENT
============================================================ */
@Composable
fun CategoryContent(modifier: Modifier = Modifier) {
    // Dummy Data List
    val articles = remember {
        listOf(
            ArticleData(1, "Universitas dan Organisasi Turki", "UKDW Perluas Jejaring dengan Universitas dan Organisasi Turki", 20, "3 Hours ago", false),
            ArticleData(2, "UKDW Meriahkan Dies Natalis ke-63", "UKDW Yogyakarta sukses menyelenggarakan Fun Run yang berlangsung meriah", 55, "7 Hours ago", true),
            ArticleData(3, "Duta Voice Raih Tiga Medali Emas", "UKDW berhasil menorehkan prestasi gemilang pada ajang 5th International", 150, "12 Hours ago", false),
            ArticleData(4, "UKDW Kunjungi Kansai University", "Kunjungan ini disambut langsung oleh Vice President Kansai University", 80, "7 Hours ago", true),
            ArticleData(5, "Universitas dan Organisasi Turki", "UKDW Perluas Jejaring dengan Universitas dan Organisasi Turki", 200, "16 Hours ago", false)
        )
    }

    Column(modifier = modifier.fillMaxSize()) {

        // --- HEADER SECTION ---
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                text = "EDUCATION",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Jelajahi seputar Education",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- HORIZONTAL CHIPS ---
            val categories = listOf("Technology", "Society", "Religion", "Sports", "Politics")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(text = category)
                }
            }
        }

        // --- ARTICLE LIST ---
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(articles) { article ->
                ArticleItemCard(article)
            }
        }
    }
}

/* ============================================================
   COMPONENTS: CHIP
============================================================ */
@Composable
fun CategoryChip(text: String) {
    Box(
        modifier = Modifier
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { /* TODO: Filter Action */ }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}

/* ============================================================
   COMPONENTS: ARTICLE ITEM CARD
============================================================ */
@Composable
fun ArticleItemCard(article: ArticleData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp) // Tinggi fix agar seragam
            .border(1.dp, Color(0xFFF0F0F0), RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Image Thumbnail (Left)
            Image(
                painter = painterResource(id = R.drawable.news), // Ganti dengan gambar artikel
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 2. Content (Right)
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title & Desc
                Column {
                    Text(
                        text = article.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = article.description,
                        fontSize = 11.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 14.sp
                    )
                }

                // Metadata (Views, Time, Favorite)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Views
                        Icon(Icons.Outlined.Visibility, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${article.views}", fontSize = 10.sp, color = Color.Gray)

                        Spacer(modifier = Modifier.width(12.dp))

                        // Time
                        Icon(Icons.Outlined.AccessTime, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = article.timeAgo, fontSize = 10.sp, color = Color.Gray)
                    }

                    // Favorite Icon
                    Icon(
                        imageVector = if (article.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (article.isFavorite) Color.Red else Color.Black,
                        modifier = Modifier.size(18.dp).clickable { /* TODO */ }
                    )
                }
            }
        }
    }
}

/* ============================================================
   BOTTOM NAVIGATION
============================================================ */
@Composable
fun CategoryBottomBar() {
    Column {
        HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = false, // Tidak ada yg aktif spesifik di desain ini
                onClick = {}
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") },
                label = { Text("Favorite") },
                selected = false,
                onClick = {}
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                label = { Text("Search") },
                selected = false, // Bisa diset true jika ini halaman search result
                onClick = {}
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                label = { Text("Profile") },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCategory() {
    DDwancanTheme { CategoryScreen() }
}