package com.naguirrel.corto1platas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.naguirrel.corto1platas.HomeScreen
import com.naguirrel.corto1platas.Operation
import com.naguirrel.corto1platas.OperationScreen

@Serializable object Home
@Serializable enum class Operation { SUMA, RESTA }
@Serializable data class OpForm(val op: Operation)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = nav,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(
                                onSuma = { nav.navigate("op/SUMA") },
                                onResta = { nav.navigate("op/RESTA") }
                            )
                        }
                        composable("op/{op}") { backStackEntry ->
                            val opStr = backStackEntry.arguments?.getString("op") ?: "SUMA"
                            val op = runCatching { Operation.valueOf(opStr) }.getOrElse { Operation.SUMA }
                            OperationScreen(op = op, onBack = { nav.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
