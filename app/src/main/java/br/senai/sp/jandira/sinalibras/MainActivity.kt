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
import br.senai.sp.jandira.sinalibras.Screens.EditarPerfil
import br.senai.sp.jandira.sinalibras.Screens.ErroQuiz
import br.senai.sp.jandira.sinalibras.Screens.Escolha
import br.senai.sp.jandira.sinalibras.Screens.Inicio
import br.senai.sp.jandira.sinalibras.Screens.Login
import br.senai.sp.jandira.sinalibras.Screens.Perfil
import br.senai.sp.jandira.sinalibras.Screens.Quiz
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
                        startDestination = "perfil/12"
                    ) {
                        composable(route = "cadastro") { Cadastro(controleDeNavegacao) }
                        composable(route = "inicio") { Inicio(controleDeNavegacao) }
                        composable(route = "login") { Login(controleDeNavegacao) }
                        composable(route = "escolha"){ Escolha(controleDeNavegacao) }
                        composable(route = "quiz"){ Quiz(controleDeNavegacao) }
                        composable(
                            "perfil/{userId}",
                            arguments = listOf(navArgument("userId") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            Perfil(controleDeNavegacao,id = backStackEntry.arguments!!.getInt("userId"))
                        }
                        composable(
                            "editaPerfil/{userId}",
                            arguments = listOf(navArgument("userId") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            EditarPerfil(controleDeNavegacao,id = backStackEntry.arguments!!.getInt("userId"))
                        }
                        composable(
                            "acerto/{porcentagem}",
                            arguments = listOf(navArgument("porcentagem") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            AcertoQuiz(controleDeNavegacao,porcentagem = backStackEntry.arguments?.getInt("porcentagem"))
                        }
                        composable(
                            "erro/{porcentagem}",
                            arguments = listOf(navArgument("porcentagem") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            ErroQuiz(controleDeNavegacao,porcentagem = backStackEntry.arguments?.getInt("porcentagem"))
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

