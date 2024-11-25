package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun ImplementacaoFutura(controleDeNavegacao: NavHostController,
                        id: String,
                        tipoUsuario: String,
                        fotoPerfil: String) {
    var focus by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)

        )

        Text(
            text = "Opss, parece que você deu de cara com uma função em andamento! \nEstamos dando o nosso melhor para finalizarmos tudo a tempo",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 60.dp),
            textAlign = TextAlign.Center
        )
    }
        if (focus) {
            Box(modifier=Modifier.fillMaxSize().background(color=Color(0x68090A1E))){
                Column(
                    modifier = Modifier
                        .width(258.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .align(Alignment.BottomCenter)
                        .padding(start=16.dp,end=16.dp,top=16.dp, bottom = 105.dp)
                ) {
                    Button(
                        onClick = {
                            focus = false
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier.offset(x = -20.dp, y = -10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.btn_cancelar),
                            contentDescription = "Botao cancelar ação",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFC7E2FE))
                            .border(1.dp, Color.Black)
                            .clickable{
                                controleDeNavegacao.navigate("postarVideo?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                            }
                            .padding(horizontal = 12.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Adicionar aula",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        )
                        Image(
                            painter = painterResource(id = R.drawable.videoaula),
                            contentDescription = "adicionar aula",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFC7E2FE))
                            .border(1.dp, Color.Black)
                            .clickable{
                                controleDeNavegacao.navigate("postarPostagem?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                            }
                            .padding(horizontal = 12.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Adicionar post",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        )
                        Image(
                            painter = painterResource(id = R.drawable.postagem),
                            contentDescription = "adicionar postagem",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }}
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
                                .clickable { focus = true },
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
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .background(color = Color(0xff3459DE))
                    )
                }
            }

        }

    }

}