package id.app.ddwancan.view.activity

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
import androidx.compose.runtime.Composable
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
import id.app.ddwancan.R
import id.app.ddwancan.ui.theme.DDwancanTheme

/* ============================================================
   ACTIVITY
============================================================ */
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { HomeScreen() } }
    }
}

/* ============================================================
   DATA MODEL + DUMMY DATA
============================================================ */
data class NewsItem(
    val title: String,
    val desc: String,
    val image: Int,
    val views: Int,
    val time: String
)

val dummyNews = listOf(
    NewsItem("Universitas dan Organisasi Turki",
        "UKDW Perluas Jejaring dengan Universitas dan Organisasi Turki",
        R.drawable.news, 20, "3 Hours ago"),

    NewsItem("UKDW Meriahkan Dies Natalis ke-63",
        "UKDW Yogyakarta sukses menggelar Fun Run meriah penuh semangat",
        R.drawable.news, 55, "7 Hours ago"),

    NewsItem("Duta Voice Raih Tiga Medali Emas",
        "Prestasi gemilang pada ajang International Bandung Choral Festival",
        R.drawable.news, 150, "12 Hours ago"),

    NewsItem("UKDW Kunjungi Kansai University",
        "Kunjungan disambut langsung Vice President Kansai University",
        R.drawable.news, 80, "7 Hours ago")
)

/* ============================================================
   HOME SCREEN (MAIN)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        HomeContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(6.dp))
                Text(
                    "D’Wacana",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF2678FF)
        )
    )
}

/* ============================================================
   MAIN CONTENT (BREAKING NEWS + LIST)
============================================================ */
@Composable
fun HomeContent(modifier: Modifier = Modifier) {
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

        items(dummyNews) { item ->
            NewsCard(item)
        }
    }
}

/* ============================================================
   BREAKING NEWS IMAGE
============================================================ */
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
   CATEGORY CHIPS
============================================================ */
@Composable
fun CategoryChips() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ChipItem("All", true)
        ChipItem("Techno")
        ChipItem("Education")
        ChipItem("Society")
    }
}

@Composable
fun ChipItem(text: String, selected: Boolean = false) {
    Surface(
        color = if (selected) Color(0xFF2678FF) else Color(0xFFEFF3FF),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text,
            fontSize = 14.sp,
            color = if (selected) Color.White else Color(0xFF2678FF),
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
        )
    }
}

/* ============================================================
   NEWS CARD (LIST ITEM)
============================================================ */
@Composable
fun NewsCard(item: NewsItem) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // LEFT IMAGE
            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
            )

            Spacer(Modifier.width(12.dp))

            // TEXT SECTION
            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(item.desc, maxLines = 2, fontSize = 13.sp)

                Spacer(Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("👁 ${item.views}", fontSize = 12.sp)
                    Spacer(Modifier.width(12.dp))
                    Text(item.time, fontSize = 12.sp)
                }
            }

            // FAVORITE ICON
            IconButton(onClick = {}) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null)
            }
        }
    }
}

/* ============================================================
   BOTTOM NAVIGATION BAR
============================================================ */
@Composable
fun BottomNavigationBar() {
    Column {
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavItem(Icons.Default.Home, "Home", true)
            NavItem(Icons.Default.Favorite, "Favorite")
            NavItem(Icons.Default.Search, "Search")
            NavItem(Icons.Default.Person, "Profile")
        }
    }
}

@Composable
fun RowScope.NavItem(icon: ImageVector, label: String, selected: Boolean = false) {
    NavigationBarItem(
        selected = selected,
        onClick = {},
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label) }
    )
}

/* ============================================================
   PREVIEW
============================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHome() {
    DDwancanTheme { HomeScreen() }
}
