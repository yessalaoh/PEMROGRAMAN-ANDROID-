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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.app.ddwancan.ui.theme.DDwancanTheme

/* ============================================================
   ACTIVITY
============================================================ */
class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { SearchScreen() } }
    }
}

/* ============================================================
   MAIN SCREEN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Scaffold(
        topBar = { SearchTopBar() },
        bottomBar = { SearchBottomBar() }
    ) { innerPadding ->
        SearchContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR (Back + Logo)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar() {
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
   SEARCH CONTENT
============================================================ */
@Composable
fun SearchContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        /* TITLE */
        item {
            Spacer(Modifier.height(16.dp))

            Text("Cari Berita", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            SearchBarField()

            Spacer(Modifier.height(20.dp))

            Text("Pencarian terakhir", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.height(12.dp))

            LastSearchChips()

            Spacer(Modifier.height(12.dp))
        }

        /* LIST BERITA */
        items(dummyNews) { item ->
            SearchNewsCard(item)
        }
    }
}

/* ============================================================
   SEARCH BAR
============================================================ */
@Composable
fun SearchBarField() {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFEDEDED),     // abu-abu lembut seperti Figma
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )

            Spacer(Modifier.width(12.dp))

            Text(
                "Cari berita...",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}


/* ============================================================
   LAST SEARCH CHIPS
============================================================ */
@Composable
fun LastSearchChips() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ChipFilter("Dies Natalis")
        ChipFilter("Duta Voice")
        ChipFilter("Beasiswa")
    }
}

@Composable
fun ChipFilter(text: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFEFF3FF)
    ) {
        Text(
            text,
            color = Color(0xFF2678FF),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}

/* ============================================================
   SEARCH CARD
============================================================ */
@Composable
fun SearchNewsCard(item: NewsItem) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {

            Image(
                painter = painterResource(item.image),
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("👁 ${item.views}", fontSize = 12.sp)
                    Spacer(Modifier.width(12.dp))
                    Text(item.time, fontSize = 12.sp)
                }
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null)
            }
        }
    }
}

/* ============================================================
   BOTTOM NAVIGATION
============================================================ */
@Composable
fun SearchBottomBar() {
    Column {
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavItem(Icons.Default.Home, "Home")
            NavItem(Icons.Default.Favorite, "Favorite")
            NavItem(Icons.Default.Search, "Search", selected = true)
            NavItem(Icons.Default.Person, "Profile")
        }
    }
}

/* ============================================================
   PREVIEW
============================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSearch() {
    DDwancanTheme { SearchScreen() }
}
