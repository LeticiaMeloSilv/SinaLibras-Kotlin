package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun Suporte(controleDeNavegacao: NavHostController,
            id: String,
            email: String,
            nome: String,
            dataNascimento: String,
            fotoPerfil: String,
            tipoUsuario: String
) {
    var conteudoDenuncia = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E7FF))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    controleDeNavegacao.navigate("configuracoes?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                },
                colors = ButtonColors(
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btn_voltar),
                    contentDescription = "Botao Voltar",
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Suporte/Denúncias",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.SupportAgent,
            contentDescription = "icone de usuario de suporte",
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Novo Chamado",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = {},
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = conteudoDenuncia.value,
            onValueChange = {
                conteudoDenuncia.value =it
            },
            label = { Text("Assunto") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

      //  Spacer(modifier = Modifier.height(16.dp))

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Receber notificação por email")
//            Spacer(modifier = Modifier.weight(1f))
//            Switch(checked = false, onCheckedChange = {}) //botao do on e off
//        }
        Icon(
            painter = painterResource(id = R.drawable.send),
            contentDescription = "Enviar",
            modifier = Modifier.size(24.dp).clickable{

            }
        )
        Spacer(modifier = Modifier.height(165.dp))

        Icon(
            painter = painterResource(id = R.drawable.logo_grande),
            contentDescription = "Logo SinaLibras",
            modifier = Modifier
                .size(114.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

//RESOLOVER O BGLH DO DO EMAIL. NÃO É NOSSO FOCO AGR


