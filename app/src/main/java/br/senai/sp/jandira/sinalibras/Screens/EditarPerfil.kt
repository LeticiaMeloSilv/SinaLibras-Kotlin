package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun EditarPerfil(controleDeNavegacao: NavHostController, recebido: String) {
    val partes = recebido.split("*")
    Log.i("USUARIO", recebido)
    val dadosUsuario = partes[0]
    val tipoUsuario = partes[1]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "BackArrow",
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 85.dp),
            text = "Editar Perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFC7E2FE))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem de perfil
        Box(
            modifier = Modifier
                .size(180.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Coloque o ID correto da imagem
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nome
        EditarCampoPerfil("Nome", "Abracadabra Souza")

        // Data de Nascimento com Ícone de calendário
        EditarCampoPerfil(
            label = "Data de Nascimento",
            placeholder = "08/17/2023",
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "BackArrow"
                )
            }
        )

        // Email
        EditarCampoPerfil("Email", "abracadabraSouza@gmail.com")

        // Altera Senha
        EditarCampoPerfil(
            label = "",
            placeholder = "Alterar Senha",
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Alterar Senha"
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão de Salvar Alterações
        Button(
            onClick = { /* Ação do botão */ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(100.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3E96)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Salvar Alterações",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EditarCampoPerfil(
    label: String,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(8.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFF8C8C8C),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC7E2FE), RoundedCornerShape(8.dp))
                .border(
                    width = 2.dp, // Espessura da borda
                    color = Color(0xFF65558F), // Cor roxa da borda
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = placeholder, fontSize = 16.sp, modifier = Modifier.weight(1f))
            trailingIcon?.invoke()
        }
    }
}