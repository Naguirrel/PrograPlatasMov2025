package com.naguirrel.lab6platas

// Librerías
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naguirrel.lab6platas.ui.theme.Lab6PlatasTheme // Usa el nombre de tu theme real

class MainActivity : ComponentActivity() { // Main del programa
    override fun onCreate(savedInstanceState: Bundle?) { // Crea la actividad
        super.onCreate(savedInstanceState) // Llama al onCreate de la superclase
        setContent { // Establece contenido
            Lab6PlatasTheme { // Llama al theme de la app (Theme.kt)
                Surface( // Establece surface
                    modifier = Modifier.fillMaxSize(), // Rellena la pantalla
                    color = MaterialTheme.colorScheme.background // Establece color de fondo
                ) {
                    ContadorApp() // Invoca la funcion
                }
            }
        }
    }
}

@Composable
fun ContadorApp() {
    // Estado del contador
    var contador by remember { mutableStateOf(0) }

    // Estadísticas
    var totalIncrementos by remember { mutableStateOf(0) }
    var totalDecrementos by remember { mutableStateOf(0) }
    var maximo by remember { mutableStateOf(0) }
    var minimo by remember { mutableStateOf(0) }

    // Historial de cambios
    val historial = remember { mutableStateListOf<Pair<Int, Boolean>>() }

    // Función para actualizar el contador y las estadísticas
    fun actualizar(valor: Int, incremento: Boolean) {
        contador = valor
        if (incremento) totalIncrementos++ else totalDecrementos++
        maximo = maxOf(maximo, contador)
        minimo = if (totalIncrementos + totalDecrementos == 1) contador else minOf(minimo, contador)
        historial.add(Pair(contador, incremento))
    }

    // Columna Principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp) // mantiene padding general
            .padding(top = 80.dp), // agrega espacio extra arriba
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // Mi nombre literalmente
        Text("Norman Aguirre Lepe", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Fila de botones de agregar y quitar
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Botón para restar
            Button(
                onClick = { actualizar(contador - 1, false) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0))
            ) {
                Text("-")
            }

            // Texto del número del contador
            Text(
                text = "$contador",
                fontSize = 40.sp,
                modifier = Modifier.padding(horizontal = 24.dp),
                fontWeight = FontWeight.Bold
            )

            // Botón para sumar
            Button(
                onClick = { actualizar(contador + 1, true) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0))
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Estadísticas en columna
        Column(horizontalAlignment = Alignment.Start) {
            Text("Total incrementos: $totalIncrementos")
            Text("Total decrementos: $totalDecrementos")
            Text("Valor máximo: $maximo")
            Text("Valor mínimo: $minimo")
            Text("Total cambios: ${totalIncrementos + totalDecrementos}")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Historial:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Historial visual en grid (full a chat)
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(historial) { (valor, incremento) ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                            if (incremento) Color(0xFF4CAF50) else Color(0xFFF44336),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "$valor", color = Color.White)
                }
            }
        }

        // Botón reiniciar
        Button(
            onClick = {
                // Reinicia el contador y las estadísticas
                contador = 0
                totalIncrementos = 0
                totalDecrementos = 0
                maximo = 0
                minimo = 0
                historial.clear()
            },
            // Aspectos del botón
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0))
        ) {
            Text("Reiniciar", color = Color.White) // Texto del botón
        }
    }
}
