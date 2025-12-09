package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AboutUsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutUsScreen(
                onBackClick = { finish() },
                onNavigateHome = { /* Navigate to Home */ },
                onNavigateFavorite = { /* Navigate to Favorite */ },
                onNavigateSearch = { /* Navigate to Search */ },
                onNavigateProfile = { /* Navigate to Profile */ },
                onTermsClick = { /* Open Terms of Service */ },
                onPrivacyClick = { /* Open Privacy Policy */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(
    onBackClick: () -> Unit = {},
    onNavigateHome: () -> Unit = {},
    onNavigateFavorite: () -> Unit = {},
    onNavigateSearch: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About us",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F6DDC)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Home", fontSize = 12.sp) },
                    selected = false,
                    onClick = onNavigateHome,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF0F6DDC),
                        selectedTextColor = Color(0xFF0F6DDC),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, "Favorite") },
                    label = { Text("Favorite", fontSize = 12.sp) },
                    selected = false,
                    onClick = onNavigateFavorite,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF0F6DDC),
                        selectedTextColor = Color(0xFF0F6DDC),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, "Search") },
                    label = { Text("Search", fontSize = 12.sp) },
                    selected = false,
                    onClick = onNavigateSearch,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF0F6DDC),
                        selectedTextColor = Color(0xFF0F6DDC),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, "Profile") },
                    label = { Text("Profile", fontSize = 12.sp) },
                    selected = false,
                    onClick = onNavigateProfile,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF0F6DDC),
                        selectedTextColor = Color(0xFF0F6DDC),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "D'Wacana",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "ID'Wacana adalah sumber terpercaya Anda untuk berita terbaru dan terkemuka. Misi kami adalah menyediakan informasi yang akurat, tidak bias, dan tepat waktu untuk memberdayakan komunitas dan mencerahkan pembaca. Kami berdedikasi untuk menyajikan beragam perspektif dan mendorong pemikiran kritis.",
                fontSize = 15.sp,
                color = Color.Black,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Visi Kami Section
            Text(
                text = "Visi Kami:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Menjadi platform berita digital terdepan yang menginspirasi diskusi bermakna dan mempromosikan warga negara yang terinformasi di seluruh dunia.",
                fontSize = 15.sp,
                color = Color.Black,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Contact Section
            val contactText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                    append("Kontak Kami: ")
                }
                withStyle(style = SpanStyle(fontSize = 15.sp)) {
                    append("Email: ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("support@dwacana.com")
                }
            }

            Text(
                text = contactText,
                color = Color.Black,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Terms and Privacy
            val termsPrivacyText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0F6DDC),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("[Terms of Service]")
                }
                withStyle(style = SpanStyle(fontSize = 15.sp)) {
                    append(" | ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0F6DDC),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("[Privacy Policy]")
                }
            }

            Text(
                text = termsPrivacyText,
                modifier = Modifier.clickable {
                    // Handle click on Terms or Privacy
                },
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutUsScreenPreview() {
    AboutUsScreen()
}