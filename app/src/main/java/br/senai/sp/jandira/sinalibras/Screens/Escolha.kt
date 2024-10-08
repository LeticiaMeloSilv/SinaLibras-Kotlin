package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
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
import br.senai.sp.jandira.sinalibras.model.Usuario

@Composable
fun Escolha(controleDeNavegacao:NavHostController) {
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
                horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Button(
            onClick = {
                controleDeNavegacao.navigate("inicio")
            },
            colors = ButtonColors(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_voltar),
                contentDescription = "Botao Voltar",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(text="Escolha como você vai participar", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xff345ADE))
        Card(modifier = Modifier
            .clickable {
                controleDeNavegacao.navigate("quiz")
            }
            .size(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Image(
                painter = painterResource(id = R.drawable.professor),
                contentDescription = "icone de professor",
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color(0x66FFFFFF))
                    .border(
                        width = 4.dp, color = Color(0xffffffff)
                    )
            )
            Text(text = "Professor", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xff345ADE))
        }}
        Card(modifier = Modifier
            .clickable {
                controleDeNavegacao.navigate("cadastro")
            }
            .size(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.aluno),
                    contentDescription = "icone de aluno",
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = Color(0x66FFFFFF))
                        .border(
                            width = 4.dp, color = Color(0xffffffff)
                        )
                )
                Text(text = "Aluno", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xff345ADE))
            }}
        }


    }