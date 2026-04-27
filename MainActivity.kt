package com.example.repaso_weatherapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repaso_weatherapp.ui.theme.Repaso_WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting(
                name = "Android")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MainData(ciudad: String, temperatura: String, HUM: String, Viento: String, Lluvia: String){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush. verticalGradient(
                    colors = listOf(
                        Color(0xFF72EAFF),
                        Color(0xFF72C2FF),
                        Color(0xFF003888)

                    )

                )
    ), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(ciudad, fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold)

        Text(temperatura, fontSize = 64.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))
        Text("🧭",
            fontSize = 85.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.2f),
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(20.dp)

        ){

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
             { Column(horizontalAlignment = Alignment.CenterHorizontally) {
                 Text("HUM", color = Color.White.copy(0.5f)
                 )

                 Text(HUM, color = Color.White,
                     fontWeight = FontWeight.Bold)
             }
                 Column(horizontalAlignment = Alignment.CenterHorizontally) {
                     Text("Viento", color = Color.White.copy(0.5f)
                     )

                     Text(Viento, color = Color.White,
                         fontWeight = FontWeight.Bold)
                 }
                 Column(horizontalAlignment = Alignment.CenterHorizontally) {
                     Text("Lluvia", color = Color.White.copy(0.5f)
                     )

                     Text(Lluvia, color = Color.White,
                         fontWeight = FontWeight.Bold)
                 }
            }


        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)

            , contentAlignment = Alignment.Center
        )
            {

            Text("ACTUALIZAR",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )

        }
    }


}

@Composable
fun climaXhora(hora: String, emoji: String, temperatura: String){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(hora, color = Color.White)
        Text(emoji)
        Text(temperatura, color = Color.White, fontWeight = FontWeight.Bold)
    }

}

@Composable
fun climaXdia(dia: String, emoji: String, tempMax: String, tempMin: String){
Column(modifier = Modifier.fillMaxWidth()) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(dia, color = Color.White)
        Text(emoji)
        Row() {
            Text(tempMax, fontWeight = FontWeight.Bold, color = Color.White)
            Text("/"+tempMin, color = Color.White.copy(alpha = 0.4f))
        }
    }

}

    Divider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 1.dp,
        color = Color.White.copy(alpha = 0.3f)
    )




}


@Composable
fun AppClima(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush. verticalGradient(
                colors = listOf(
                    Color(0xFFA0B6EB),
                    Color(0xFFEA52F8),
                    Color(0xFF0066FF)

                )

            )

        )
        .padding(16.dp)

    ) {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("El Salvador",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )
            Text("25°C",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 65.sp
            )
            Text("Soleado",
                color = Color.White.copy(0.5f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Pronostico por horas", color = Color.White.copy(alpha = 0.5f))

        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            climaXhora("Ahora", "🌞","25°")
            climaXhora("14:00", "☀️","26°")
            climaXhora("16:00", "🌥️","24°")
            climaXhora("18:00", "🌦️","22°")
            climaXhora("20:00", "🌛","20°")
        }

        Spacer(modifier = Modifier.height(20.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(7.dp))
                .background(color = Color.White.copy(alpha = 0.2f))
                .padding(10.dp)
        ){
            Column() {

                Text("DETALLES", color = Color.White.copy(alpha = 0.6f))

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Humedad", color = Color.White.copy(alpha = 0.6f))
                        Text("65%", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Viento", color = Color.White.copy(alpha = 0.6f))
                        Text("12 km/h", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Presion", color = Color.White.copy(alpha = 0.6f))
                        Text("1012hPa", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("UV", color = Color.White.copy(alpha = 0.6f))
                        Text("5", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

            }
        }


        Spacer(modifier = Modifier.height(13.dp))

        Text("PRONOSTICO SEMANAL", color = Color.White.copy(alpha = 0.6f))

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(color = Color.White.copy(alpha = 0.2f))
                .padding(10.dp)

        ){
            Column() {
                climaXdia("Lun", "☀️", "28°", "22°")
                climaXdia("Mar", "🌥️", "27°", "21°")
                climaXdia("Mie", "🌦️", "26°", "20°")
                climaXdia("Jue", "⛈️", "25°", "16°")
                climaXdia("Vie", "⚛️", "99°", "25°")





            }
        }

    }


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Repaso_WeatherAppTheme {
//AppClima()
        MainData("sivar", "pijo de calor", "50%", "0.5 m/s", "hoy ho")
    }
}