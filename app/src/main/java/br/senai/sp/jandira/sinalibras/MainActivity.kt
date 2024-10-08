package br.senai.sp.jandira.sinalibras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.sinalibras.Screens.Escolha
import br.senai.sp.jandira.sinalibras.Screens.Inicio
import br.senai.sp.jandira.sinalibras.Screens.Login
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
                        startDestination = "inicio"
                    ) {
                        composable(route = "cadastro") { Cadastro(controleDeNavegacao) }
                        composable(route = "inicio") { Inicio(controleDeNavegacao) }
                        composable(route = "login") { Login(controleDeNavegacao) }
                        composable(route = "escolha"){ Escolha(controleDeNavegacao) }
                        composable(route = "quiz"){ Quiz(controleDeNavegacao) }

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

