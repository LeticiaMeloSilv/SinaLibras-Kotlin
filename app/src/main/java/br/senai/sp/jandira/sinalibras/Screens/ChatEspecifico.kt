package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R


@Composable
fun ChatEspecifico(
    controleDeNavegacao: NavHostController,
    idDoOutroUsuario: String,
    id: String,
    tipoUsuario: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2C8E2))
    ) {
        ChatHeader()
        Spacer(modifier = Modifier.weight(1f))
        MessageInputField()
    }
}

@Composable
fun ChatHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.perfil),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "JAMES SMITH", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(Icons.Default.Phone, contentDescription = "Chamada")
        }
        IconButton(onClick = { }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Mais opções")
        }
    }
}

@Composable
fun ChatBubble(text: String, isSentByUser: Boolean) {
    val backgroundColor = if (isSentByUser) Color(0xFF4B6EA9) else Color.White
    val textColor = if (isSentByUser) Color.White else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            Text(text = text, color = textColor)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField() {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Mensagem...", color = Color(0xFFFFFFFF)) },
            modifier = Modifier
                .weight(1f)
                .border(
                    BorderStroke(1.dp, Color(0xFF04509C)),
                    shape = RoundedCornerShape(24.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { }) {
            Icon(Icons.Default.Send, contentDescription = "Enviar")
        }
    }
}
