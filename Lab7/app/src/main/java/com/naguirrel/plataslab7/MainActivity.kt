package com.naguirrel.plataslab7

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.naguirrel.plataslab7.Notification
import com.naguirrel.plataslab7.NotificationType
import com.naguirrel.plataslab7.generateFakeNotifications
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.luminance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(Modifier.fillMaxSize()) {
                    NotificationsScreen(
                        onBack = { /* finish() si deseas */ }
                    )
                }
            }
        }
    }
}

/* -------------------- THEME (Material You seguro por versión) -------------------- */

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val ctx = LocalContext.current
        if (dark) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
    } else {
        if (dark) darkColorScheme() else lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}

/* -------------------- UI -------------------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    var filterInfo by remember { mutableStateOf(false) } // Informativas
    var filterCaps by remember { mutableStateOf(false) } // Capacitaciones

    val all = remember { generateFakeNotifications() }

    val list = remember(filterInfo, filterCaps, all) {
        when {
            filterInfo && !filterCaps -> all.filter { it.type == NotificationType.GENERAL }
            !filterInfo && filterCaps -> all.filter { it.type == NotificationType.NEW_MEETING }
            else -> all
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B5E20)
                )
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                "Tipos de notificaciones",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = filterInfo,
                    onClick = { filterInfo = !filterInfo },
                    label = { Text("Informativas") }
                )
                FilterChip(
                    selected = filterCaps,
                    onClick = { filterCaps = !filterCaps },
                    label = { Text("Capacitaciones") }
                )
            }

            Spacer(Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp
            ) {
                LazyColumn(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = list, key = { it.id }) { n ->
                        NotificationItem(n)
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationItem(n: Notification) {
    val (bg, icon) = when (n.type) {
        NotificationType.GENERAL -> Color(0xFFFFE0B2) to Icons.Filled.Notifications // naranja claro
        NotificationType.NEW_MEETING -> Color(0xFFB2EBF2) to Icons.Filled.DateRange     // cian claro
    }
    val iconTint = if (bg.luminance() > 0.5f) Color.Black else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(bg),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconTint)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(n.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(2.dp))
            Text(n.body, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(Modifier.width(8.dp))
        Text(n.sendAt, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
