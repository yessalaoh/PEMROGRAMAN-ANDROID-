package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.app.ddwancan.R

data class EducationArticle(
    val id: Int,
    val title: String,
    val description: String,
    val views: Int,
    val timeAgo: String,
    val isFavorite: Boolean = false
)

class EducationCategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationCategoryScreen(
                onBackClick = { finish() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationCategoryScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("Technology") }
    val filters = listOf("Technology", "Education", "Science", "Society")

    var articles by remember {
        mutableStateOf(
            listOf(
                EducationArticle(1, "UKDW Meriahkan Dies Natalis ke-63", "UKDW Yogyakarta sukses menyelenggarakan Fun Run yang berlangsung meriah dan penuh semangat", 76, "8 Hours ago", false),
                EducationArticle(2, "Duta Voice Raih Tiga Medali Emas", "UKDW berhasil menorehkan prestasi gemilang pada ajang 5th International Bandung Choral Festival", 17, "3 Hours ago", true),
                EducationArticle(3, "UKDW Kunjungi Kansai University", "Kunjungan ini disambut langsung oleh Vice President Kansai University, Prof. Osamu Takeuchi", 20, "7 Hours ago", false),
                EducationArticle(4, "Duta Voice Raih Tiga Medali Emas", "UKDW berhasil menorehkan prestasi gemilang pada ajang 5th International Bandung Choral Festival", 17, "3 Hours ago", false),
                EducationArticle(5, "Duta Voice Raih Tiga Medali Emas", "UKDW berhasil menorehkan prestasi gemilang pada ajang 5th International Bandung Choral Festival", 17, "3 Hours ago", true)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kategori", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F6DDC))
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
                NavigationBarItem(icon = { Icon(Icons.Default.Home, "Home") }, label = { Text("Home", fontSize = 12.sp) }, selected = true, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0F6DDC), selectedTextColor = Color(0xFF0F6DDC), unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.Star, "Favorite") }, label = { Text("Favorite", fontSize = 12.sp) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0F6DDC), selectedTextColor = Color(0xFF0F6DDC), unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.Search, "Search") }, label = { Text("Search", fontSize = 12.sp) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0F6DDC), selectedTextColor = Color(0xFF0F6DDC), unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.Person, "Profile") }, label = { Text("Profile", fontSize = 12.sp) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0F6DDC), selectedTextColor = Color(0xFF0F6DDC), unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray, indicatorColor = Color.Transparent))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(Color(0xFFF5F5F5))
        ) {
            Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
                Text("EDUCATION", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text("Jelajahi seputar education", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter, fontSize = 14.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF0F6DDC).copy(alpha = 0.1f),
                            selectedLabelColor = Color(0xFF0F6DDC),
                            containerColor = Color.Transparent,
                            labelColor = Color.Gray
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selectedFilter == filter,
                            borderColor = if (selectedFilter == filter) Color(0xFF0F6DDC) else Color.LightGray,
                            selectedBorderColor = Color(0xFF0F6DDC)
                        )
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(articles) { article ->
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                            Image(painterResource(R.drawable.logo1), article.title, Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f).height(80.dp)) {
                                Text(article.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                Spacer(Modifier.height(4.dp))
                                Text(article.description, fontSize = 12.sp, color = Color.Gray, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Visibility, "Views", Modifier.size(14.dp), Color.Gray)
                                        Spacer(Modifier.width(4.dp))
                                        Text(article.views.toString(), fontSize = 12.sp, color = Color.Gray)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.AccessTime, "Time", Modifier.size(14.dp), Color.Gray)
                                        Spacer(Modifier.width(4.dp))
                                        Text(article.timeAgo, fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                            }
                            IconButton(onClick = { articles = articles.map { if (it.id == article.id) it.copy(isFavorite = !it.isFavorite) else it } }, Modifier.size(24.dp)) {
                                Icon(if (article.isFavorite) Icons.Filled.Star else Icons.Outlined.Star, "Favorite", tint = if (article.isFavorite) Color(0xFFFFC107) else Color.Gray, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EducationCategoryScreenPreview() {
    EducationCategoryScreen()
}