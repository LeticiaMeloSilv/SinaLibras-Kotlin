package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun SobreNos(
    controleDeNavegacao: NavHostController,
    id: String,
    email: String,
    nome: String,
    dataNascimento: String,
    fotoPerfil: String,
    tipoUsuario: String
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E8FF))
            .padding(16.dp)
            .verticalScroll(scrollState)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
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
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Sobre Nós",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Olá nós somos a OpenOwl e você está aproveitando um de nossos produtos!",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Person",
                modifier = Modifier
                    .size(80.dp)
                    .weight(0.3f),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "O SinaLibras é uma plataforma online e totalmente gratuita para conectar pessoas, ensinando a todos os nossos usuários a Língua de Sinais Brasileira e tudo sobre a comunidade de pessoas surdas, ensurdecidas e deficientes auditivos.",
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Person",
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
                    .weight(1f)
            ) {

                Text(
                    text = "Você sabia que, no Brasil, cerca de 5% da população possui algum grau de surdez?",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Foi pensando nessas pessoas que a plataforma foi desenvolvida, e para nos ajudar em todo processo de criação de conteúdo e informações, contamos com a ajuda de pessoas que têm propriedade para ensinar. 'Como garantem que isso aconteça em uma plataforma totalmente gratuita?' Nossos professores não são contratados, pois caso fossem, teríamos de cobrar pela utilização da plataforma, então, nossos professores são afiliados de ONGs e conhecedores da comunidade, para participarem, eles realizam um questionário antes de criarem suas contas, o usuário deve alcançar 70% de acertos para termos certeza de que ele é capacitado para espalhar informação e inclusão em nossa plataforma. A plataforma conta com um sistema de videoaulas, postagens de texto e videochamada, você tem a liberdade de explorar todo o conteúdo postado, comentar suas dúvidas e curtir suas videoaulas preferidas!",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Mas afinal, quem de fato somos nós?",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Person",
                modifier = Modifier
                    .size(80.dp)
                    .weight(0.3f),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "A OpenOwl é uma Start up criada com o objetivo inicial de produzir um TCC de desenvolvimento de sistemas, onde os desenvolvedores são: Ana Luiza, Gabriel Barros, Julia Mendes, Letícia Melo, Paloma Vessoleck e Ruan Calsolari. Somos da área da educação e temos o foco no desenvolvimento de soluções tecnológicas para um ensino acessível e inclusivo, que atenda às necessidades de diversas comunidades.",
            fontSize = 16.sp,
            color = Color.Black
        )


        Spacer(modifier = Modifier.height(24.dp))
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color.White)
//                .padding(16.dp)
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
//                    fontSize = 14.sp,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = { /* Ação de doação */ },
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5AFE))
//                ) {
//                    Text(text = "DOAR", color = Color.White)
//                }
//            }
//        }
//        Box(modifier = Modifier.fillMaxSize()) {
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "perfil",
//                modifier = Modifier
//                    .size(width = 170.dp, height = 170.dp)
//                    .align(Alignment.BottomCenter)
//            )
//        }

}
}
