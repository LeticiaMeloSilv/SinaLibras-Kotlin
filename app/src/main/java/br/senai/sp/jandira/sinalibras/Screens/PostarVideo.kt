package br.senai.sp.jandira.sinalibras.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostVideo(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    dataNascimento: String,
    nome: String,
    email: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E2FE))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .width(238.dp)
                .height(149.dp)
                .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Default.Upload,
                contentDescription = "Upload Video",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "upload video",
                color = Color.Gray,
                modifier = Modifier.padding(top = 60.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
            )
            Text(text = "Foto", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Titulo do video:",
            modifier = Modifier.align(Alignment.Start)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .height(29.dp)
                .fillMaxWidth()
                .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Descrição do video:",
            modifier = Modifier.align(Alignment.Start)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Nível", color = Color.Black, modifier = Modifier.align(Alignment.Start))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                checked = true,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
            )
            Text(text = "Básico", color = Color.Black)

            Checkbox(
                checked = false,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
            )
            Text(text = "Intermediário", color = Color.Black)

            Checkbox(
                checked = false,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
            )
            Text(text = "Avançado", color = Color.Black)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.Start)
        ) {
            Text(
                text = "Módulo",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "seta pra baixo",
                tint = Color(0xFF3B5EDF)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B5EDF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(127.dp)
                    .height(43.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "POSTAR", fontWeight = FontWeight.Bold)
            }
        }
    }
}

