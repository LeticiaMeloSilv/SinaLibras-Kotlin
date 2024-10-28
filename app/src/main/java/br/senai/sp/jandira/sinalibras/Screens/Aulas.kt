package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Aulas(controleDeNavegacao: NavHostController, id: String, idModulo: String, tipoUsuario: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFC7E2FE)) // Fundo azul claro da tela
//    ) {
//        // Cabeçalho
//        Row(
//            modifier = Modifier
//                .background(color = Color(0xFFC7E2FE)) // Cor do fundo do cabeçalho
//                .height(100.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "logo",
//                modifier = Modifier
//                    .size(width = 114.dp, height = 146.dp)
//                    .padding(8.dp)
//            )
//
//            Image(
//                painter = painterResource(id = R.drawable.perfil),
//                contentDescription = "perfil",
//                modifier = Modifier
//                    .size(width = 70.dp, height = 50.dp)
//                    .align(Alignment.CenterVertically)
//                    .padding(8.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Conteúdo dos cards
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly // Distribui os cards igualmente
//            ) {
//                CardComponent(title = "Saudações")
//                CardComponent(title = "Comidas")
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                CardComponent(title = "Saudações")
//                CardComponent(title = "Comidas")
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                CardComponent(title = "Animais")
//                CardComponent(title = "Animais")
//            }
//        }
//
//        // Rodapé com ícones e botão centralizado
//        Spacer(modifier = Modifier.weight(1f)) // Preenche o espaço até o rodapé
//        BottomNavigationBar()
//    }
//}
//
//@Composable
//fun CardComponent(title: String) {
//    Card(
//        modifier = Modifier
//            .size(width = 146.61.dp, height = 115.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Icon(
//                imageVector = Icons.Filled.People,
//                contentDescription = "",
//                tint = Color.Black
//            )
//            Text(
//                text = title,
//                color = Color.Black
//            )
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar() {
//    Row(
//        modifier = Modifier
//            .padding(bottom = 35.dp)
//            .fillMaxWidth()
//            .background(Color(0xFFA5D1FF)), // Cor de fundo azul claro
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_chat),
//            contentDescription = "Chat",
//            modifier = Modifier.size(30.dp)
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.ic_atividades),
//            contentDescription = "Atividades",
//            modifier = Modifier.size(30.dp)
//        )
//        // Botão centralizado com um ícone diferente
//        Card(
//            modifier = Modifier.size(60.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB3B)), // Cor amarela do botão
//            shape = androidx.compose.foundation.shape.CircleShape // Formato circular
//        ) {
//            Box(
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.rank),
//                    contentDescription = "logo",
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .size(height = 70.dp, width = 70.dp)
//                )
//            }
//        }
//        Icon(
//            painter = painterResource(id = R.drawable.ic_aulas),
//            contentDescription = "Aulas",
//            modifier = Modifier.size(30.dp)
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.ic_feed),
//            contentDescription = "Feed",
//            modifier = Modifier.size(30.dp)
//        )
//    }
}
