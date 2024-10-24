package br.senai.sp.jandira.sinalibras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.sinalibras.Screens.AcertoQuiz
import br.senai.sp.jandira.sinalibras.Screens.ChatEspecifico
import br.senai.sp.jandira.sinalibras.Screens.Configuracoes
import br.senai.sp.jandira.sinalibras.Screens.EditarPerfil
import br.senai.sp.jandira.sinalibras.Screens.EditarSenha
import br.senai.sp.jandira.sinalibras.Screens.ErroQuiz
import br.senai.sp.jandira.sinalibras.Screens.Escolha
import br.senai.sp.jandira.sinalibras.Screens.Inicio
import br.senai.sp.jandira.sinalibras.Screens.Login
import br.senai.sp.jandira.sinalibras.Screens.Perfil
import br.senai.sp.jandira.sinalibras.Screens.Quiz
import br.senai.sp.jandira.sinalibras.Screens.VideoInfo
import br.senai.sp.jandira.sinalibras.ui.theme.SinaLibrasTheme
import com.jakewharton.threetenabp.AndroidThreeTen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContent {
            SinaLibrasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val controleDeNavegacao = rememberNavController()
                    NavHost(
                        navController = controleDeNavegacao,
                        startDestination = "perfil/1"
                    ) {
                        composable(route = "inicio") { Inicio(controleDeNavegacao) }
                        composable(route = "login") { Login(controleDeNavegacao) }
                        composable(route = "escolha"){ Escolha(controleDeNavegacao) }
                        composable(
                            "cadastro/{emailProfessor}",
                            arguments = listOf(navArgument("emailProfessor") { type = NavType.StringType }) // Change to StringType
                        ) { backStackEntry ->
                            val idFornecido = backStackEntry.arguments?.getString("emailProfessor") ?: ""
                            Cadastro(controleDeNavegacao, emailProfessor = idFornecido)
                        }

                            composable(
                            "quiz/{recebido}",
                            arguments = listOf(navArgument("recebido") { type = NavType.StringType }) // Change to StringType
                        ) { backStackEntry ->
                            val idFornecido = backStackEntry.arguments?.getString("recebido") ?: ""
                            Quiz(controleDeNavegacao, recebido = idFornecido)
                        }

                        composable(
                            "perfil/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getString("recebido")
                                ?.let { Perfil(controleDeNavegacao,recebido = it) }
                        }
                        composable(
                            "configuracoes/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            backStackEntry.arguments!!.getString("recebido")
                                ?.let { Configuracoes(controleDeNavegacao, recebido = it) }
                        }
                        composable(
                            "sobreNos/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            backStackEntry.arguments!!.getString("recebido")
                                ?.let { Configuracoes(controleDeNavegacao, recebido = it) }
                        }
                        composable(
                            "editaPerfil/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            backStackEntry.arguments!!.getString("recebido")
                                ?.let { EditarPerfil(controleDeNavegacao, recebido = it) }
                        }
                        composable(
                            "editarSenha/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            backStackEntry.arguments!!.getString("recebido")
                                ?.let { EditarSenha(controleDeNavegacao, recebido = it) }
                        }

                        composable(
                            "acerto/{recebido}",
                            arguments = listOf(navArgument("recebido") { type =
                                NavType.StringType })
                        ) { backStackEntry ->
                            AcertoQuiz(controleDeNavegacao, recebido = backStackEntry.arguments?.getString("recebido"))
                        }
                        composable(
                            "erro/{porcentagem}",
                            arguments = listOf(navArgument("porcentagem") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            ErroQuiz(controleDeNavegacao,porcentagem = backStackEntry.arguments?.getInt("porcentagem"))
                        }
                        composable(
                            "chatEspecifico/{recebido}",
                            arguments = listOf(navArgument("recebido") { type = NavType.StringType }) // Change to StringType
                        ) { backStackEntry ->
                            val recebido = backStackEntry.arguments?.getString("recebido") ?: ""
                            ChatEspecifico(controleDeNavegacao, recebido = recebido)
                        }
                        composable(
                            "videoInfo/{recebido}",
                            arguments = listOf(navArgument("recebido") { type = NavType.StringType }) // Change to StringType
                        ) { backStackEntry ->
                            val idFornecido = backStackEntry.arguments?.getString("recebido") ?: ""
                            VideoInfo(controleDeNavegacao, recebido = idFornecido)
                        }


                    }

                }
            }
        }
    }
}
//usei esse no AndroidManifest para ter acesso a internet
//  <uses-permission android:name="android.permission.INTERNET"/>
//e esses pra conseguir usar protocolo http, mas dps é pra tirar esses tres comandos e arquivo
//        android:networkSecurityConfig="@xml/network_security_config"
//        android:usesCleartextTraffic="true"

