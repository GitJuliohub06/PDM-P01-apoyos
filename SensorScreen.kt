package com.example.labo_02_julioflores_00018824

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// La lógica del sensor se mantiene intacta porque está perfecta
@Composable
fun useSensor(sensorType: Int): List<Float> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = remember(sensorType) { sensorManager.getDefaultSensor(sensorType) }
    var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    DisposableEffect(sensorType) {
        if (sensor != null) {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.let { sensorValues = it.toList() }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            onDispose { sensorManager.unregisterListener(listener) }
        } else {
            onDispose { }
        }
    }
    return sensorValues
}

@Composable
fun SensorScreen(modifier: Modifier = Modifier, onVolver: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp) // Esto distribuye los elementos uniformemente
    ) {
        Column {
            Text(text = "Sensores")
            Text(text = "Lectura en tiempo real del sensor de luz y del giroscopio.")
        }

        LightSensor()

        GyroscopeSensor()

        Spacer(modifier = Modifier.weight(1f)) // Empuja el botón al fondo

        Button(onClick = onVolver, modifier = Modifier.fillMaxWidth()) {
            Text("Volver al menú")
        }
    }
}

@Composable
fun LightSensor() {
    val lightValues = useSensor(Sensor.TYPE_LIGHT)
    val light = lightValues.getOrNull(0) ?: 0f
    val progress = (light / 1000f).coerceIn(0f, 1f)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Sensor de Luz")
        Text(text = "Mide la intensidad de luz ambiental.")
        Text(text = "${String.format("%.2f", light)} lx")

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun GyroscopeSensor() {
    val gyroscopeValues = useSensor(Sensor.TYPE_GYROSCOPE)
    val x = gyroscopeValues.getOrNull(0) ?: 0f
    val y = gyroscopeValues.getOrNull(1) ?: 0f
    val z = gyroscopeValues.getOrNull(2) ?: 0f

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Giroscopio")
        Text(text = "Detecta la rotación del dispositivo.")

        SensorValueRow(eje = "X", valor = x)
        SensorValueRow(eje = "Y", valor = y)
        SensorValueRow(eje = "Z", valor = z)
    }
}

@Composable
fun SensorValueRow(eje: String, valor: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Eje $eje")
        Text(text = String.format("%.4f", valor))
    }
}