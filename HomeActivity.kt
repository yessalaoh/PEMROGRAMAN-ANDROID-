//package com.example.bebas.view.activity
package id.app.ddwancan.view

import android.os.Bundle
impor androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.app.ddwancan.R
import id.app.ddwancan.ui.theme.DDwancanTheme
import id.app.ddwancan.viewmodel.NewsViewModel
import id.app.ddwancan.viewmodel.NewsViewModelFactory
import id.app.ddwancan.data.repository.NewsRepositoryImpl
import id.app.ddwancan.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DDwancanTheme {

                // 🔗 INJECT API → REPOSITORY → VIEWMODEL
                val repository = NewsRepositoryImpl(RetrofitInstance.api)
                val viewModel: NewsViewModel = viewModel(
                    factory = NewsViewModelFactory(repository)
                )

                MainScreenWithDrawer(viewModel)
            }
        }
    }

    // ==============================
    // DRAWER + SCAFFOLD
    // ==============================
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreenWithDrawer(viewModel: NewsViewModel) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent {
                        scope.launch { drawerState.close() }
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("D'Wacana") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF0057FF),
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                },
                bottomBar = { BottomNavigationBar() }
            ) { innerPadding ->
                HomeLayout(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = viewModel
                )
            }
        }
    }

    // ==============================
    // DRAWER CONTENT
    // ==============================
    @Composable
    fun DrawerContent(onClick: () -> Unit) {
        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0057FF))
                    .padding(24.dp)
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.logo1),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("D'Wacana", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Portal Berita UKDW", color = Color.White.copy(0.8f))
                }
            }

            DrawerMenuItem(Icons.Default.Home, "Home", onClick)
            DrawerMenuItem(Icons.Default.Favorite, "Favorite", onClick)
            DrawerMenuItem(Icons.Default.Search, "Search", onClick)
            DrawerMenuItem(Icons.Default.Person, "Profile", onClick)
        }
    }

    @Composable
    fun DrawerMenuItem(icon: ImageVector, title: String, onClick: () -> Unit) {
        NavigationDrawerItem(
            icon = { Icon(icon, null) },
            label = { Text(title) },
            selected = false,
            onClick = onClick
        )
    }

    // ==============================
    // HOME CONTENT (API INTEGRATED)
    // ==============================
    @Composable
    fun HomeLayout(
        modifier: Modifier = Modifier,
        viewModel: NewsViewModel
    ) {
        val articles by viewModel.news.collectAsState()

        val beritaList = articles.map {
            Berita(
                title = it.title,
                desc = it.summary,
                image = R.drawable.logo1,
                time = it.publishedAt,
                views = 0
            )
        }

        LaunchedEffect(Unit) {
            viewModel.fetchNews()
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(beritaList) { berita ->
                BeritaCard(berita)
            }
        }
    }

    // ==============================
    // UI MODEL
    // ==============================
    data class Berita(
        val title: String,
        val desc: String,
        val image: Int,
        val time: String,
        val views: Int
    )

    @Composable
    fun BeritaCard(berita: Berita) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 3.dp
        ) {
            Row(
                Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(berita.image),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(berita.title, fontWeight = FontWeight.Bold)
                    Text(berita.desc, maxLines = 2)
                    Row {
                        Text("👁 ${berita.views}")
                        Spacer(Modifier.width(8.dp))
                        Text(berita.time)
                    }
                }
            }
        }
    }

    // ==============================
    // BOTTOM NAV
    // ==============================
    @Composable
    fun BottomNavigationBar() {
        NavigationBar {
            NavigationBarItem(true, {}, { Icon(Icons.Default.Home, null) }, { Text("Home") })
            NavigationBarItem(false, {}, { Icon(Icons.Default.Favorite, null) }, { Text("Favorite") })
            NavigationBarItem(false, {}, { Icon(Icons.Default.Search, null) }, { Text("Search") })
            NavigationBarItem(false, {}, { Icon(Icons.Default.Person, null) }, { Text("Profile") })
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewHome() {
        DDwancanTheme {
            // preview tanpa API
        }
    }
}
