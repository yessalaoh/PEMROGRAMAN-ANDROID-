package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
        bottomBar = { ProfileBottomBar() },
        containerColor = Color.White // Pastikan background putih bersih
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

    // Data State
    var name by remember { mutableStateOf("71220197_GianP") }
    var email by remember { mutableStateOf("GianP@students.ukdw.ac.id") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp), // Padding kiri-kanan agar rapi
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(30.dp))

        ProfileAvatar()

        Spacer(Modifier.height(16.dp))

        // Username Handle di bawah foto
        Text(
            text = "@71220917_GianP",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(Modifier.height(40.dp))

        // 🔵 BAGIAN TEXTFIELD YANG DIPERBAIKI (Desain Custom)

        // Input Name
        ProfileInputRow(
            label = "Name :",
            value = name,
            onValueChange = { name = it },
            icon = Icons.Outlined.Person
        )

        Spacer(Modifier.height(24.dp))

        // Input Email
        ProfileInputRow(
            label = "Email :",
            value = email,
            onValueChange = { email = it },
            icon = Icons.Outlined.Email
        )

        Spacer(Modifier.height(50.dp))

        EditProfileButton()
    }
}

/* ============================================================
   CUSTOM INPUT ROW (Sesuai Desain Figma)
============================================================ */
@Composable
fun ProfileInputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Ikon di sebelah kiri (Lingkaran hitam tebal/Outline)
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Label kecil di atas ("Name :")
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // TextField tanpa border kotak, hanya teks (Editable)
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    ),
                    cursorBrush = SolidColor(Color(0xFF2678FF)), // Kursor warna biru
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Garis pemisah di bawah (Divider)
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

/* ============================================================
   PROFILE AVATAR
============================================================ */
@Composable
fun ProfileAvatar() {
    val borderColor = Color(0xFF2678FF)
    val borderWidth = 3.dp
    val avatarSize = 110.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(avatarSize)
    ) {
        // Border Biru
        Box(
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(Color.White) // Jarak putih antara foto dan border
                .border(borderWidth, borderColor, CircleShape)
                .padding(borderWidth) // Padding agar foto tidak menempel border
        ) {
            // Foto Profil
            Image(
                painter = painterResource(R.drawable.profilefoto), // Pastikan nama file sesuai
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

/* ============================================================
   EDIT PROFILE BUTTON
============================================================ */
/* ============================================================
   EDIT PROFILE BUTTON (UPDATED)
   Disamakan formatnya dengan tombol Simpan/Batal
============================================================ */
/* ============================================================
   EDIT PROFILE BUTTON (FIXED)
   Ukuran disamakan persis dengan Action Button (Simpan)
============================================================ */
@Composable
fun EditProfileButton() {
    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2678FF)),
        modifier = Modifier
            // Menambahkan padding 20.dp di kiri-kanan agar lebarnya menyusut
            // sama persis seperti container tombol 'Simpan' di EditProfileActivity
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "EDIT PROFIL",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
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
        HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        NavigationBar(containerColor = Color.White) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = false,
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
                selected = false,
                onClick = {}
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                label = { Text("Profile") },
                selected = true,
                onClick = {},
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFE3F2FD),
                    selectedIconColor = Color(0xFF2678FF)
                )
            )
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
