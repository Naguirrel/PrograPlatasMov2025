package com.naguirrel.corto1platas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pantalla 1
@Composable
fun HomeScreen(onSuma: () -> Unit, onResta: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onSuma, modifier = Modifier.fillMaxWidth()) { Text("Suma") }
            Spacer(Modifier.height(16.dp))
            Button(onClick = onResta, modifier = Modifier.fillMaxWidth()) { Text("Resta") }
        }
        Text(
            text = "Norman Aguirre - 24479",
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

// Pantalla 2
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationScreen(op: Operation, onBack: () -> Unit) {
    var aText by remember { mutableStateOf("") }
    var bText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    val title = if (op == Operation.SUMA) "Suma" else "Resta"

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(title) },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { inner ->
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(inner)
                    .padding(24.dp)
            ) {
                Text("Formulario de $title", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = aText,
                    onValueChange = { aText = it },
                    label = { Text("Valor A") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = bText,
                    onValueChange = { bText = it },
                    label = { Text("Valor B") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {
                        val a = aText.toDoubleOrNull()
                        val b = bText.toDoubleOrNull()
                        result = if (a != null && b != null) {
                            if (op == Operation.SUMA) (a + b).toString() else (a - b).toString()
                        } else "Ingrese números válidos"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Operar") }

                Spacer(Modifier.height(24.dp))
                Text("Resultado", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))
                Text(result ?: "", fontSize = 18.sp)
            }

        }
    }
}

@Composable
fun SmallTopAppBar(title: @Composable () -> Unit, navigationIcon: @Composable () -> Unit) {

}
