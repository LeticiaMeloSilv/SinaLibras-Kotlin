package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun Inicio(controleDeNavegacao: NavHostController) {
    //gradiente do background
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFC7E2FE), Color(0xFF345ADE)),
        start = Offset.Zero,
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    Column(
        modifier = Modifier
            .background(brush = gradientBrush)
            .fillMaxSize()
            .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.logoGrande),
            contentDescription = "logotipo da aplicacao",
            modifier = Modifier.size(75.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(text = "Conectando Pessoas")
        Text(text = "transformando sinais")
        Spacer(modifier = Modifier.height(26.dp))
        Button( onClick = {}) {
            Text(text = "ACESSAR CONTA")
        }
        Spacer(modifier = Modifier.height(26.dp))
        Text(text = "Ainda não possui uma conta?")
        Button( onClick = {}) {
            Text(text = "CRIAR CONTA")
        }
    }
}

