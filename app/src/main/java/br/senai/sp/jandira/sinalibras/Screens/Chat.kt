package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Chat(controleDeNavegacao: NavHostController, id: String, tipoUsuario: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E2FE))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color(0xFF04509C),
                    shape = RoundedCornerShape(10.dp)

                )
                .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "pesquisar por usuario",
                    tint = Color(0xFF04509C)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "pesquisar por usuarios...",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Professores disponiveis",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(

        ) {
            items(5) {
                Card(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(width = 64.dp, height = 64.dp),
                    shape = CircleShape,
                    colors = CardDefaults
                        .cardColors(
                            containerColor = Color(0xFFCF06F0)
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Recentes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 22.dp, ))

        Spacer(modifier = Modifier.height(20.dp))


        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(319.dp)
                .height(349.dp)
        ) {



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(38.dp)
                    )

                    Text(
                        text = "nome do usuario",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 63.dp)
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(38.dp)
                    )

                    Text(
                        text = "nome do usuario",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 126.dp)
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(38.dp)
                    )

                    Text(
                        text = "nome do usuario",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 189.dp)
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(38.dp)
                    )

                    Text(
                        text = "nome do usuario",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 252.dp)
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(38.dp)
                    )

                    Text(
                        text = "nome do usuario",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                }
            }
        }
    }
}
