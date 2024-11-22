package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultFeed
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Feed(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String
) {
    val painter: Painter =
        if (fotoPerfil != "" && fotoPerfil != "null" && fotoPerfil.isNotEmpty()) {
            rememberAsyncImagePainter(model = fotoPerfil)
        } else {
            painterResource(id = R.drawable.perfil)
        }

    val callFeed = RetrofitFactory().getPostagensService().getAllFeed()
    callFeed.enqueue(object : Callback<ResultFeed> {
        override fun onResponse(p0: Call<ResultFeed>, p1: Response<ResultFeed>) {
            val alunoResponse = p1.body()
            if (alunoResponse == null) {
                Log.i("ERRO_MODULOS", p1.toString())
            } else {
                Log.i("TAG", alunoResponse.toString())
                //funcionouState = true
                //dadosModulos = alunoResponse
            }
        }

        override fun onFailure(p0: Call<ResultFeed>, p1: Throwable) {
            Log.i("ERRO_PERFIL", p1.toString())
        }


    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 55.dp, top = 16.dp, start = 16.dp, end = 16.dp)
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
                )

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFA5D1FF), CircleShape)
                        .clickable {
                            controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                        .clip(CircleShape), // Aplica a forma circular
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFA5D1FF), shape = RoundedCornerShape(24.dp))
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
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Descrição da imagem",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )


                    Text(
                        text = "nome_do_usuario",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(180.dp)//aq vai vim a capa do video
                        .background(Color(0xFF5CAF4C), shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {

                }
            }

            Spacer(modifier = Modifier.height(16.dp))


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
                        .padding(16.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Descrição da imagem",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "nome_do_usuario",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
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
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            colors = CardColors(
                containerColor = Color(0xFFA5D1FF),
                contentColor = Color(0xFFA5D1FF),
                disabledContentColor = Color(0xFFA5D1FF),
                disabledContainerColor = Color(0xFFA5D1FF)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 38.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { controleDeNavegacao.navigate("chat?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat Icon",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Chat",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") }

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.atividades),
                        contentDescription = "Activities Icon",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Atividades",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)

                    )
                }


                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFA5D1FF))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    if (tipoUsuario == "aluno") {
                        Image(
                            painter = painterResource(
                                id = R.drawable.rank
                            ),
                            contentDescription = "Profile Icon",
                            modifier = Modifier
                                .size(45.dp)
                                .offset((-10).dp, 0.dp)
                                .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") },
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Image(
                            painter = painterResource(
                                id = R.drawable.mais
                            ),
                            contentDescription = "Profile Icon",
                            modifier = Modifier
                                .size(45.dp)
                                .offset((-10).dp, 0.dp)
                                .clickable { controleDeNavegacao.navigate("criar?id=${id}&tipoUsuario=${tipoUsuario}") },
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            controleDeNavegacao.navigate("modulos?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.videos),
                        contentDescription = "Classes Icon",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Aulas",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.feed),
                        contentDescription = "Menu Icon",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Feed",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Box(modifier = Modifier
                        .height(2.dp)
                        .background(color = Color(0xff3459DE)))
                }
            }

        }
    }
}

