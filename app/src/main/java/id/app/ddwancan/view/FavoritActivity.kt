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
class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { FavoriteScreen() } }
    }
}

/* ============================================================
   FAVORITE SCREEN MAIN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = { FavoriteTopBar() },
        bottomBar = { FavoriteBottomBar() }
    ) { innerPadding ->
        FavoriteContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR (Back + Logo + Text)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO: Back action */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "D’Wacana",
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
   FAVORITE CONTENT
============================================================ */
@Composable
fun FavoriteContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        item {
            Spacer(Modifier.height(16.dp))

            Text(
                "Artikel Tersimpan",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                "Daftar berita favoritmu",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(12.dp))
        }

        items(dummyNews) { item ->
            FavoriteCard(item)
        }
    }
}

/* ============================================================
   FAVORITE CARD (Reuse NewsCard but Heart is filled)
============================================================ */
@Composable
fun FavoriteCard(item: NewsItem) {
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

            NewsImage(item)

            Spacer(Modifier.width(12.dp))

            FavoriteCardText(item, modifier = Modifier.weight(1f))

            IconButton(onClick = {}) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red)
            }
        }
    }
}

@Composable
private fun NewsImage(item: NewsItem) {
    Image(
        painter = painterResource(item.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .background(Color.LightGray, RoundedCornerShape(10.dp))
    )
}

@Composable
private fun FavoriteCardText(item: NewsItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Text(item.desc, maxLines = 2, fontSize = 13.sp)

        Spacer(Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("👁 ${item.views}", fontSize = 12.sp)
            Spacer(Modifier.width(12.dp))
            Text(item.time, fontSize = 12.sp)
        }
    }
}

/* ============================================================
   BOTTOM NAVIGATION (Same as Home)
============================================================ */
@Composable
fun FavoriteBottomBar() {
    Column {
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavItem(Icons.Default.Home, "Home")
            NavItem(Icons.Default.Favorite, "Favorite", true)
            NavItem(Icons.Default.Search, "Search")
            NavItem(Icons.Default.Person, "Profile")
        }
    }
}


/* ============================================================
   PREVIEW
============================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFavorite() {
    DDwancanTheme { FavoriteScreen() }
}
