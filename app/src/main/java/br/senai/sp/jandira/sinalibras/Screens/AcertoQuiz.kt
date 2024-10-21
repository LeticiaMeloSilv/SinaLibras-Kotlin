package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.sinalibras.R

@Composable
fun AcertoQuiz(controleDeNavegacao: NavHostController, recebido: String?) {
    val partes = recebido?.split("*")
    val porcentagem = partes?.get(0)
    val emailFornecido = partes?.get(1)
    Column(
        modifier = Modifier
            .background(color = Color(0xFFC7E2FE))
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Row(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // Centraliza horizontalmente
            verticalAlignment = Alignment.CenterVertically // Centraliza verticalmente
        ) {
            Column( // Column para empilhar a imagem e o texto
                horizontalAlignment = Alignment.CenterHorizontally // Centraliza dentro da Column
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "perfil",
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                )
                Text(
                    text = "Total de acertos: $porcentagem%",
                    fontSize = 26.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )


            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centraliza os itens verticalmente
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PARABÉNS!",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color(0xFF22367C)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Você passou no teste, pode ensinar Lingua de Sinais aos estudantes",
                    fontSize = 24.sp,
                    color = Color(0xFF22367C),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp)) // Aumenta o espaço entre o texto e o botão

            Button(
                onClick = { /* Ação do botão */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3E96)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Criar Conta",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        controleDeNavegacao.navigate("cadastro/${emailFornecido}")
                    })
            }
        }

    }
}