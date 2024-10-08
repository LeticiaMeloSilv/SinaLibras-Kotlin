package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_grande),
            contentDescription = "logotipo da aplicacao",
            modifier = Modifier
                .size(250.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Conectando Pessoas",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff345ADE)
        )
        Text(
            text = "transformando sinais",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xff345ADE)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                controleDeNavegacao.navigate("login")
            },
            colors = ButtonColors(
                containerColor = Color(0xffEFF3FF),
                contentColor = Color(0xffEFF3FF),
                disabledContentColor = Color(0xffEFF3FF),
                disabledContainerColor = Color(0xffEFF3FF)
            ),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(40.dp, 18.dp)
        ) {
            Text(
                text = "ACESSAR CONTA",
                color = Color(0xff345ADE),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
        Text(text = "Ainda não possui uma conta?", color = Color(0xffEFF3FF))
        Button(
            onClick = {
                controleDeNavegacao.navigate("escolha")

            },
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = "CRIAR CONTA",
                color = Color(0xffEFF3FF),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

