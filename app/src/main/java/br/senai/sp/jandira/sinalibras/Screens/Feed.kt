package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import coil.compose.rememberAsyncImagePainter


@Composable
fun Feed(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String
) {
    val painter: Painter =
        if (fotoPerfil !="" && fotoPerfil != "null" && fotoPerfil.isNotEmpty()) {
            rememberAsyncImagePainter(model = fotoPerfil)
        } else {
            painterResource(id = R.drawable.perfil)
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE1ECF8))
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .background(Color(0xFFE1ECF8))
                    .padding(8.dp)
            )

            Image(
                painter =painter,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFE1ECF8), CircleShape)
                    .padding(8.dp)
                    .clickable {
                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}")
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF73A7D4), shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Buscar", color = Color.Black)
            }

            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "Filtrar",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Descrição da imagem",
                modifier = Modifier
                    .size(28    .dp)
                    .padding(end = 8.dp)
            )


            Text(
                text = "Dicas bbbbbbbb",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF5CAF4C), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                tint = Color(0xFF1D4FCF),
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(445.dp)
                .height(436.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(137.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "foto",
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color(0xFFE1ECF8), CircleShape)
                    )


                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "José Silva")

                        Text(
                            text = "Dia internacional do surdo\n" +
                                    "Comunicação... Continuar lendo",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }

    }
}

