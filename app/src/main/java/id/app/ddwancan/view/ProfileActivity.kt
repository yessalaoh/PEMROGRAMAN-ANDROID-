package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { ProfileScreen() } }
    }
}

/* ============================================================
   MAIN PROFILE SCREEN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = { ProfileTopBar() },
        bottomBar = { ProfileBottomBar() }
    ) { innerPadding ->
        ProfileContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        title = {
            Text(
                text = "Profil",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF2678FF)
        )
    )
}

/* ============================================================
   PROFILE CONTENT
============================================================ */
@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(24.dp))

        ProfileAvatar()

        Spacer(Modifier.height(10.dp))

        Text(
            text = "@71220917_GianP",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(24.dp))

        ProfileInfoCard(icon = Icons.Default.Person, label = "Name :", value = "71220197_GianP")
        Spacer(Modifier.height(12.dp))
        ProfileInfoCard(icon = Icons.Default.Email, label = "Email :", value = "GianP@students.ukdw.ac.id")

        Spacer(Modifier.height(40.dp))

        EditProfileButton()
    }
}

/* ============================================================
   PROFILE AVATAR
============================================================ */
@Composable
fun ProfileAvatar() {
    val borderColor = Color(0xFF2678FF)
    val borderWidth = 4.dp
    val avatarSize = 110.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(avatarSize)
    ) {
        // BORDER LUAR BIRU
        Box(
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(borderColor)
                .padding(borderWidth)
        ) {
            // FOTO PROFIL BULAT
            Image(
                painter = painterResource(R.drawable.profilefoto),
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.LightGray, CircleShape)
            )
        }
    }
}


/* ============================================================
   USER INFO ROW
============================================================ */
@Composable
fun ProfileInfoCard(icon: ImageVector, label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column {
                Text(label, fontSize = 12.sp, color = Color.Gray)
                Text(value, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(Modifier.height(8.dp))

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
    }
}

/* ============================================================
   EDIT PROFILE BUTTON
============================================================ */
@Composable
fun EditProfileButton() {
    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2678FF)),
        modifier = Modifier
            .width(150.dp)           // ⬅️ Lebar tombol diperkecil
            .height(48.dp),   // Posisi berada di tengah
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            "EDIT PROFIL",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


/* ============================================================
   BOTTOM NAVIGATION
============================================================ */
@Composable
fun ProfileBottomBar() {
    Column {
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavItem(Icons.Default.Home, "Home")
            NavItem(Icons.Default.Favorite, "Favorite")
            NavItem(Icons.Default.Search, "Search")
            NavItem(Icons.Default.Person, "Profile", selected = true)
        }
    }
}

/* ============================================================
   PREVIEW
============================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProfile() {
    DDwancanTheme { ProfileScreen() }
}
