package com.example.labo_02_julioflores_00018824


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.toString

@Composable
fun NameListScreen(modifier: Modifier = Modifier,
                   onVolver: () -> Unit
) {

    var listaUsuarios: MutableList<String> = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onVolver
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {
            val usuario: MutableState<String> = remember { mutableStateOf("") }
            TextField(
                value = usuario.value,
                onValueChange = {
                    usuario.value = it
                },

                )

            Button(onClick = {
                listaUsuarios.add(usuario.value)
            }) {
                Text("Guardar")
            }

            Row(modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                Text("Listado de nombres y posicion en la lista",
                    modifier = Modifier.width(150.dp))

                Button(onClick = {listaUsuarios.clear()}, ) {
                    Text("Limpiar")
                }

            }

            LazyColumn(modifier = Modifier.border(color = Color.Blue, width = 2.dp, shape = RoundedCornerShape(size=8.dp))
                .fillMaxWidth()
                .weight(0.7f)) {
                itemsIndexed(listaUsuarios.toList()) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item
                        )
                        Text(
                            text = (index + 1).toString()
                        )
                    }
                }
            }

        }
    }
}