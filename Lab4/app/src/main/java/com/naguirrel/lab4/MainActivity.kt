package com.naguirrel.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            FistComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FistComponent(){
    Box(
        Modifier.fillMaxSize()
            .border(10.dp, Color.Green),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_uvg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(350.dp),
            alpha = 0.3f
        )
    }
    Column (
        modifier = Modifier
        .fillMaxSize()
        .border(10.dp, Color.Green)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row {
            Text(text = "Universidad del Valle", fontSize = 35.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Row {
            Text(text = "de Guatemala", fontSize = 35.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Row {
            Text(text = "", fontSize = 20.sp, color = Color.Black)
        }
        Box{
            Text(text = "Programación de plataformas Móviles", fontSize = 20.sp, color = Color.Black)
        }
        Box {
            Text(text = "Sección 30", fontSize = 20.sp, color = Color.Black)
        }
        Box{
            Text(text = "", fontSize = 20.sp, color = Color.Black)
        }
        Row {
            Text(text = "INTEGRANTES         ", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = "Norman Aguirrre\n Diego Guevara \n Miguel Carranza", fontSize = 20.sp)
        }
        Box{
            Text(text = "", fontSize = 20.sp, color = Color.Black)
        }
        Row {
            Text(text = "CATEDRÁTICO         ", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = "Juan Carlos Durini", fontSize = 20.sp)
        }
        Box{
            Text(text = "", fontSize = 20.sp, color = Color.Black)
        }
        Box{
            Text(text = "Norman Aguirre Lepe", fontSize = 20.sp, color = Color.Black)
        }
        Box{
            Text(text = "Carné: 24479", fontSize = 20.sp, color = Color.Black)
        }
    }
}