package com.chat.komalkumbarportfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.ChevronRight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Komal Kumbar",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                DrawerItem("Home", Icons.Default.Home) { /* Navigate */ }
                DrawerItem("About", Icons.Default.Person) { /* Navigate */ }
                DrawerItem("Skills", Icons.Default.Star) { /* Navigate */ }
                //DrawerItem("Timeline", Icons.Default.Timeline) { /* Navigate */ }
                DrawerItem("Projects", Icons.Default.Build) { /* Navigate */ }
                DrawerItem("Contact", Icons.Default.Email) { /* Navigate */ }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Komal Kumbar") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            PortfolioContent(Modifier.padding(padding))
        }
    }
}

@Composable
fun DrawerItem(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun PortfolioContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        HomeSection()
        AboutSection()
        SkillsSection()
        TimelineSection()
        ProjectsSection()
        ContactSection()
    }
}

@Composable
fun HomeSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("I’m", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Komal Kumbar",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            "ANDROID DEVELOPER | 2+ YEARS EXPERIENCE",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AboutSection() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("About Me", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Row {
            Image(
                painter = rememberAsyncImagePainter("https://i.postimg.cc/qq8pHYYx/img1.png"),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text("I am an Android Developer with 2+ years...")
                Bullet("MVVM & MVP architecture")
                Bullet("Firebase & REST APIs")
                Bullet("Material Design & UI/UX")
            }
        }
    }
}

@Composable
fun Bullet(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.ChevronRight, contentDescription = null)
        Spacer(Modifier.width(4.dp))
        Text(text)
    }
}

@Composable
fun SkillsSection() {
    val skills = listOf(
        "Kotlin" to 0.85f,
        "Java" to 0.80f,
        "Android Studio" to 0.90f,
        "Firebase" to 0.75f,
        "Jetpack Compose" to 0.70f,
        "REST APIs" to 0.65f,
    )

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Skills", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        skills.forEach { (label, percent) ->
            Text(label)
            LinearProgressIndicator(progress = percent, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun TimelineSection() {
    val timeline = listOf(
        "2023 - Present" to "Android Developer – Real-world impact apps",
        "2021 - 2023" to "Freelance Projects – UI/UX focus",
        "2019 - 2021" to "Education – First Android apps"
    )
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Timeline", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        timeline.forEach { (date, desc) ->
            Text("$date\n$desc", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun ProjectsSection() {
    val projects = listOf(
        "Chat App" to "Realtime chat with Firebase, notifications.",
        "Leads CRM" to "Lead & task manager with team features.",
        "Leave Management" to "HR app for attendance & approvals."
    )

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Projects", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        projects.forEach { (title, desc) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(title, fontWeight = FontWeight.Bold)
                    Text(desc)
                }
            }
        }
    }
}

@Composable
fun ContactSection() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contact", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(48.dp))
        Text("komalkumbar@gmail.com")

        Spacer(Modifier.height(16.dp))

        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(48.dp))
        Text("+91-9876543210")

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AccountBox, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("LinkedIn: /komal-kumbar")
        }
    }
}
