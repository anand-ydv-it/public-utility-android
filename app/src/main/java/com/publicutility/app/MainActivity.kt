
package com.publicutility.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PublicUtilityApp()
        }
    }
}

data class Utility(
    val name: String,
    val emoji: String,
    val category: String,
    val description: String
)

private val utilities = listOf(
    Utility("Calculator", "🧮", "Tools", "Everyday calculations"),
    Utility("Age Calculator", "🎂", "Tools", "Calculate your age"),
    Utility("Unit Converter", "📏", "Tools", "Convert common units"),
    Utility("Percentage Calculator", "💯", "Tools", "Calculate percentages"),
    Utility("Date Calculator", "📅", "Tools", "Calculate date differences"),
    Utility("Alarm & Timer", "⏰", "Tools", "Time management"),
    Utility("Notes", "📝", "Productivity", "Save your notes"),
    Utility("To-Do List", "✅", "Productivity", "Organize your tasks"),
    Utility("QR Scanner", "▦", "Tools", "Scan QR codes"),
    Utility("Internet Speed", "🌐", "Internet", "Check connection speed"),
    Utility("Weather", "🌤️", "Information", "Weather information"),
    Utility("News", "📰", "Information", "Latest news"),
    Utility("Dictionary", "📖", "Education", "Word meanings"),
    Utility("Study Planner", "🎓", "Education", "Plan your studies"),
    Utility("Exam Updates", "📚", "Education", "Exam information"),
    Utility("Currency Converter", "💱", "Finance", "Convert currencies"),
    Utility("EMI Calculator", "🏦", "Finance", "Calculate loan EMI"),
    Utility("GST Calculator", "🧾", "Finance", "Calculate GST"),
    Utility("Document Guide", "📄", "Services", "Document information"),
    Utility("Government Schemes", "🏛️", "Services", "Scheme information"),
    Utility("Emergency Numbers", "🆘", "Services", "Important helpline numbers"),
    Utility("Nearby Services", "📍", "Services", "Find useful services"),
    Utility("Image Tools", "🖼️", "Media", "Image utilities"),
    Utility("Text Tools", "🔤", "Media", "Useful text utilities"),
    Utility("Help & Support", "💬", "Other", "Get app support")
)

private val categories = listOf(
    "All", "Tools", "Productivity", "Education",
    "Finance", "Services", "Internet", "Information", "Media", "Other"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicUtilityApp() {
    var search by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedUtility by remember { mutableStateOf<Utility?>(null) }

    val filteredUtilities = utilities.filter {
        (selectedCategory == "All" || it.category == selectedCategory) &&
        it.name.contains(search, ignoreCase = true)
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF65A9FF),
            background = Color(0xFF0B1020),
            surface = Color(0xFF151D30)
        )
    ) {
        Scaffold(
            containerColor = Color(0xFF0B1020),
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                "Public Utility",
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp
                            )
                            Text(
                                "Your everyday digital companion",
                                fontSize = 11.sp,
                                color = Color.LightGray
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF111A2D),
                        titleContentColor = Color.White
                    )
                )
            }
        ) { padding ->

            if (selectedUtility == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(Modifier.height(14.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1C3153)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.padding(20.dp)) {
                            Text(
                                "Everything in one place",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                "Useful tools and public services, together.",
                                color = Color(0xFFD2E2FF),
                                fontSize = 13.sp
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                "25+ FEATURES",
                                color = Color(0xFF8FC1FF),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Search services and tools") },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF65A9FF),
                            unfocusedBorderColor = Color(0xFF39445A)
                        )
                    )

                    Spacer(Modifier.height(14.dp))

                    Text(
                        "Explore Categories",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        categories.take(4).forEach { category ->
                            FilterChip(
                                selected = selectedCategory == category,
                                onClick = { selectedCategory = category },
                                label = { Text(category, fontSize = 11.sp) }
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "All Services",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(10.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredUtilities) { utility ->
                            UtilityCard(utility) {
                                selectedUtility = utility
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(20.dp)
                ) {
                    TextButton(onClick = { selectedUtility = null }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        Spacer(Modifier.width(6.dp))
                        Text("Back")
                    }

                    Spacer(Modifier.height(30.dp))

                    Text(
                        selectedUtility!!.emoji,
                        fontSize = 54.sp
                    )

                    Spacer(Modifier.height(14.dp))

                    Text(
                        selectedUtility!!.name,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        selectedUtility!!.description,
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.height(20.dp))

                    Text(
                        "This service is planned for a future update.",
                        color = Color(0xFF8FC1FF),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun UtilityCard(
    utility: Utility,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF151F33)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(utility.emoji, fontSize = 27.sp)
            Spacer(Modifier.height(9.dp))
            Text(
                utility.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                utility.category,
                color = Color(0xFF9AAAC5),
                fontSize = 11.sp
            )
        }
    }
}
