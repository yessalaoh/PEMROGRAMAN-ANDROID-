package id.app.ddwancan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DDwancanTheme { EditProfileScreen() } }
    }
}

/* ============================================================
   MAIN SCREEN
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen() {
    Scaffold(
        topBar = { EditProfileTopBar() },
        bottomBar = { EditProfileBottomNav() }
    ) { innerPadding ->
        EditProfileContent(Modifier.padding(innerPadding))
    }
}

/* ============================================================
   TOP APP BAR (Back — Edit Profil — Settings)
============================================================ */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        title = {
            Text(
                "Edit Profil",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
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
   MAIN CONTENT
============================================================ */
@Composable
fun EditProfileContent(modifier: Modifier = Modifier) {

    var name by remember { mutableStateOf("71220197_reader") }
    var email by remember { mutableStateOf("GianP@students.ukdw.ac.id") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(20.dp))

        EditProfileAvatar()

        Spacer(Modifier.height(8.dp))

        Text("@71220917_GianP", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

        Spacer(Modifier.height(4.dp))

        Text(
            "Change Image",
            color = Color(0xFF2678FF),
            fontSize = 13.sp,
            modifier = Modifier.clickable { /* TODO */ }
        )

        Spacer(Modifier.height(24.dp))

        ProfileEditField(label = "Name :", icon = Icons.Default.Person, value = name) {
            name = it
        }
        Spacer(Modifier.height(12.dp))

        ProfileEditField(label = "Email :", icon = Icons.Default.Email, value = email) {
            email = it
        }
        Spacer(Modifier.height(12.dp))

        ProfileEditField(
            label = "Confirm Password :",
            icon = Icons.Default.Lock,
            value = password,
            isPassword = true
        ) {
            password = it
        }

        Spacer(Modifier.height(30.dp))

        SaveCancelButtons()
    }
}

/* ============================================================
   PROFILE AVATAR (Bulat + Border Biru)
============================================================ */
@Composable
fun EditProfileAvatar() {
    val borderColor = Color(0xFF2678FF)
    val borderWidth = 4.dp
    val size = 110.dp

    Box(modifier = Modifier.size(size), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(borderColor)
                .padding(borderWidth)
        ) {
            Image(
                painter = painterResource(R.drawable.profilefoto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

/* ============================================================
   TEXT FIELD ROW
============================================================ */
@Composable
fun ProfileEditField(
    label: String,
    icon: ImageVector,
    value: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {

        Text(label, fontSize = 13.sp, color = Color.Gray)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(45.dp)
        ) {
            Icon(icon, contentDescription = null, tint = Color.Black)

            Spacer(Modifier.width(10.dp))

            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(0.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                singleLine = true,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        }

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
    }
}

/* ============================================================
   SAVE & CANCEL BUTTONS
============================================================ */
@Composable
fun SaveCancelButtons() {
    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2678FF)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("SIMPAN", color = Color.White, fontWeight = FontWeight.Bold)
    }

    Spacer(Modifier.height(10.dp))

    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("BATAL", color = Color.Black, fontWeight = FontWeight.Medium)
    }
}

/* ============================================================
   BOTTOM NAV
============================================================ */
@Composable
fun EditProfileBottomNav() {
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
fun PreviewEditProfile() {
    DDwancanTheme { EditProfileScreen() }
}
