package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.app.ddwancan.R
import id.app.ddwancan.ui.theme.DDwancanTheme

// Warna utama aplikasi
val PrimaryBlue = Color(0xFF2678FF)

/* ============================================================
   ACTIVITY
============================================================ */
class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { EditProfileScreen() } }
    }
}

/* ============================================================
   MAIN EDIT PROFILE SCREEN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen() {
    Scaffold(
        topBar = { EditProfileTopBar() },
        bottomBar = { ProfileBottomBar() }, // Menggunakan BottomBar yang sama agar konsisten
        containerColor = Color.White
    ) { innerPadding ->
        EditProfileContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR (Judul Berubah, Tanpa Icon Setting)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO: Handle Back navigation */ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        title = {
            Text(
                text = "Edit Profil",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PrimaryBlue
        )
    )
}

/* ============================================================
   EDIT PROFILE CONTENT
============================================================ */
@Composable
fun EditProfileContent(modifier: Modifier = Modifier) {

    // Data State untuk Input
    // Menggunakan nilai contoh seperti di gambar
    var name by remember { mutableStateOf("71220197_reader") }
    var email by remember { mutableStateOf("GianP@students.ukdw.ac.id") }
    var confirmPassword by remember { mutableStateOf("password123") } // Contoh password

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(30.dp))

        // Bagian Foto dan Ganti Foto
        ProfileAvatarSection()

        Spacer(Modifier.height(40.dp))

        // --- FORM INPUT ---

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
            icon = Icons.Outlined.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(Modifier.height(24.dp))

        // Input Confirm Password (BARU)
        // Menggunakan visualTransformation untuk mengubah teks jadi titik
        ProfileInputRow(
            label = "Confirm Password :",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            icon = Icons.Outlined.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(50.dp))

        // --- TOMBOL AKSI (SIMPAN & BATAL) ---
        ActionButtons()

        Spacer(Modifier.height(30.dp)) // Spacer tambahan di bawah agar tidak terlalu mepet bottom bar
    }
}

/* ============================================================
   AVATAR SECTION WITH CHANGE TEXT
============================================================ */
@Composable
fun ProfileAvatarSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Menggunakan komponen Avatar yang sama
        ProfileAvatar()

        Spacer(Modifier.height(12.dp))

        // Username Handle
        Text(
            text = "@71220917_GianP",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(Modifier.height(8.dp))

        // Teks "Change Image" yang bisa diklik
        Text(
            text = "Change Image",
            color = PrimaryBlue,
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { /* TODO: Handle change image */ }
        )
    }
}

/* ============================================================
   CUSTOM INPUT ROW (Reusable)
   - Ditambahkan parameter untuk Password dan Keyboard Type
============================================================ */
@Composable
fun ProfileInputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Ikon di sebelah kiri
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Label kecil di atas
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // TextField tanpa border (Editable)
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    visualTransformation = visualTransformation, // Untuk password
                    cursorBrush = SolidColor(PrimaryBlue),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Garis pemisah di bawah
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}


/* ============================================================
   ACTION BUTTONS (SIMPAN & BATAL)
============================================================ */
@Composable
fun ActionButtons() {
    // Ukuran tombol seragam
    val buttonModifier = Modifier
        .fillMaxWidth()
        .height(50.dp)

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp), // Jarak antar tombol
        modifier = Modifier.padding(horizontal = 20.dp) // Padding kiri kanan agar tidak full width
    ) {
        // Tombol SIMPAN (Biru)
        Button(
            onClick = { /* TODO: Handle Simpan */ },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            modifier = buttonModifier,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "SIMPAN",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        // Tombol BATAL (Abu-abu terang)
        Button(
            onClick = { /* TODO: Handle Batal */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)), // Warna abu terang
            modifier = buttonModifier,
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                "BATAL",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Gray // Warna teks abu gelap
            )
        }
    }
}

/* ============================================================
   PREVIEW
============================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewEditProfile() {
    DDwancanTheme { EditProfileScreen() }
}
