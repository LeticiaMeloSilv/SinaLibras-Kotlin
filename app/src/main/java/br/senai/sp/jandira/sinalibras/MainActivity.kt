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
import br.senai.sp.jandira.sinalibras.Screens.Cadastro
import br.senai.sp.jandira.sinalibras.Screens.EditarPerfil
import br.senai.sp.jandira.sinalibras.Screens.EditarSenha
import br.senai.sp.jandira.sinalibras.Screens.ErroQuiz
import br.senai.sp.jandira.sinalibras.Screens.Escolha
import br.senai.sp.jandira.sinalibras.Screens.Inicio
import br.senai.sp.jandira.sinalibras.Screens.Login
import br.senai.sp.jandira.sinalibras.Screens.Perfil
import br.senai.sp.jandira.sinalibras.Screens.Quiz
import br.senai.sp.jandira.sinalibras.Screens.SobreNos
import br.senai.sp.jandira.sinalibras.Screens.VideoInfo
import br.senai.sp.jandira.sinalibras.ui.theme.SinaLibrasTheme
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.senai.sp.jandira.sinalibras.Screens.Aulas
import br.senai.sp.jandira.sinalibras.Screens.Chat
import br.senai.sp.jandira.sinalibras.Screens.Feed
import br.senai.sp.jandira.sinalibras.Screens.Modulos
import br.senai.sp.jandira.sinalibras.Screens.PerfilOutroUsuario
import br.senai.sp.jandira.sinalibras.Screens.PostPostagem
import br.senai.sp.jandira.sinalibras.Screens.PostVideo
import br.senai.sp.jandira.sinalibras.Screens.Suporte
import com.google.firebase.FirebaseApp
import com.jakewharton.threetenabp.AndroidThreeTen

//caso mude o ipv4 do note, muda o ip configumado no res/xml/network_security_config e no retrofit
class MainActivity : ComponentActivity() {
    private lateinit var getContent: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)//coisinha q pega data atual
        FirebaseApp.initializeApp(this)//coisinha q ativa o firebase
        var imageUri by mutableStateOf<Uri?>(null)
//isso daq é pra abrir a galeria do usuario
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                Log.i("CALMA", "URI recebida: $it")
            }
        }

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



                        composable(route = "inicio") { Inicio(controleDeNavegacao) }//FEITO



                        composable(route = "login") { Login(controleDeNavegacao) }//FEITO



                        composable(route = "escolha") { Escolha(controleDeNavegacao) }//FEITO



                        composable(//FEITO
                            "cadastro/{emailProfessor}",
                            arguments = listOf(navArgument("emailProfessor") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val emailProfessor =
                                backStackEntry.arguments?.getString("emailProfessor") ?: ""
                            Cadastro(controleDeNavegacao, emailProfessor = emailProfessor)
                        }



                        composable(//FEITO(MUDAR LOGICA DE PEGAR VIDEO)
                            "quiz?idFornecido={idFornecido}&emailFornecido={emailFornecido}",
                            arguments = listOf(
                                navArgument("idFornecido") { type = NavType.StringType },
                                navArgument("emailFornecido") { type = NavType.StringType },

                                )
                        ) { backStackEntry ->
                            val idFornecido =
                                backStackEntry.arguments?.getString("idFornecido") ?: ""
                            val emailFornecido =
                                backStackEntry.arguments?.getString("emailFornecido") ?: ""

                            Quiz(
                                controleDeNavegacao = controleDeNavegacao,
                                idFornecido = idFornecido,
                                emailFornecido = emailFornecido
                            )
                        }



                        composable(//FEITO
                            "perfil?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType },
                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""


                            Perfil(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }


                        composable(//FEITO(ACHO Q FEITO)
                            "outroPerfil?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}&idOutroUsuario={idOutroUsuario}&tipoOutroUsuario={tipoOutroUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("idOutroUsuario") { type = NavType.StringType },
                                navArgument("tipoOutroUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType },


                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            val idOutroUsuario = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoOutroUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""


                            PerfilOutroUsuario(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                idOutroUsuario = idOutroUsuario,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario,
                                        tipoOutroUsuario = tipoOutroUsuario

                            )
                        }



                        composable(//FEITO
                            "configuracoes?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento =
                                backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Configuracoes(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                email = email,
                                nome = nome,
                                dataNascimento = dataNascimento,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario
                            )
                        }



                        composable(//FEITO(INSERIR INFORMACOES REAIS)
                            "sobreNos?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento =
                                backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            SobreNos(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                email = email,
                                nome = nome,
                                dataNascimento = dataNascimento,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario
                            )
                        }



                        composable(//FEITO
                            "editarPerfil?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento =
                                backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            EditarPerfil(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                email = email,
                                nome = nome,
                                dataNascimento = dataNascimento,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario,
                                getContent = { getContent.launch("image/*") },
                                initialImageUri = imageUri
                            )
                        }



                        composable(//FAZER
                            "editarSenha?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento =
                                backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            EditarSenha(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                email = email,
                                nome = nome,
                                dataNascimento = dataNascimento,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario
                            )
                        }



                        composable(//FEITO
                            "acerto?porcentagem={porcentagem}&emailFornecido={emailFornecido}",
                            arguments = listOf(
                                navArgument("porcentagem") { type = NavType.StringType },
                                navArgument("emailFornecido") { type = NavType.StringType },

                                )
                        ) { backStackEntry ->
                            val porcentagem =
                                backStackEntry.arguments?.getString("porcentagem") ?: ""
                            val emailFornecido =
                                backStackEntry.arguments?.getString("emailFornecido") ?: ""

                            AcertoQuiz(
                                controleDeNavegacao = controleDeNavegacao,
                                porcentagem = porcentagem,
                                emailFornecido = emailFornecido
                            )
                        }



                        composable(//FEITO
                            "erro?porcentagem={porcentagem}&tempoRestante={tempoRestante}",
                            arguments = listOf(
                                navArgument("porcentagem") { type = NavType.StringType },
                                navArgument("tempoRestante") { type = NavType.StringType },)
                        ) { backStackEntry ->
                            val porcentagem =
                                backStackEntry.arguments?.getString("porcentagem") ?: ""
                            val tempoRestante =
                                backStackEntry.arguments?.getString("tempoRestante") ?: ""

                            ErroQuiz(
                                controleDeNavegacao = controleDeNavegacao,
                                porcentagem = porcentagem,
                                tempoRestante = tempoRestante
                            )
                        }



                        composable(//FAZER
                            "chatEspecifico?idOutroUsuario={idOutroUsuario}}&id={id}&tipoUsuario={tipoUsuario}&tipoOutroUsuario={tipoOutroUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("idOutroUsuario") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("tipoOutroUsuario") { type = NavType.StringType },
                                navArgument("id") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },

                                )
                        ) { backStackEntry ->
                            val idOutroUsuario =
                                backStackEntry.arguments?.getString("idDoOutroUsuario") ?: ""
                            val tipoOutroUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""

                            ChatEspecifico(
                                controleDeNavegacao = controleDeNavegacao,
                                idOutroUsuario = idOutroUsuario,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil,
                                tipoOutroUsuario=tipoOutroUsuario
                            )
                        }



                        composable(//FEITO
                            "video?idDoVideo={idDoVideo}&idModulo={idModulo}&nomeModulo={nomeModulo}&id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("idDoVideo") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("id") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("idModulo") { type = NavType.StringType },
                                navArgument("nomeModulo") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            val idDoVideo = backStackEntry.arguments?.getString("idDoVideo") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val idModulo = backStackEntry.arguments?.getString("idModulo") ?: ""
                            val nomeModulo = backStackEntry.arguments?.getString("nomeModulo") ?: ""
                            VideoInfo(
                                controleDeNavegacao = controleDeNavegacao,
                                idDoVideo = idDoVideo,
                                idModulo = idModulo,
                                nomeModulo = nomeModulo,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }

                        composable(
                            "modulos?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",

                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            Modulos(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }

                        composable(
                            "feed?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },


                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""


                            Feed(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }

                        composable(
                            "aulas?idModulo={idModulo}&nomeModulo={nomeModulo}&id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("idModulo") { type = NavType.StringType },
                                navArgument("nomeModulo") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                            )
                        ) { backStackEntry ->
                            val idModulo = backStackEntry.arguments?.getString("idModulo") ?: ""
                            val nomeModulo = backStackEntry.arguments?.getString("nomeModulo") ?: ""
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            Aulas(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                idModulo = idModulo,
                                nomeModulo = nomeModulo,
                                fotoPerfil = fotoPerfil
                            )
                        }
                        composable(
                            "chat?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                            )


                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""

                            Chat(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }

                        composable(
                            "suporte?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento =
                                backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Suporte(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                email = email,
                                nome = nome,
                                dataNascimento = dataNascimento,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario
                            )
                        }



                        composable(
                            "postarPostagem?id={id}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            PostPostagem(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario,
                                getContent = { getContent.launch("image/*") },
                                initialImageUri = imageUri
                            )
                        }




                        composable(
                            "postarVideo?id={id}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("fotoPerfil") {
                                    type = NavType.StringType;nullable = true
                                },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario =
                                backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            PostVideo(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                fotoPerfil = fotoPerfil,
                                tipoUsuario = tipoUsuario,
                                getContent = { getContent.launch("video/*") },
                                initialImageUri = imageUri
                            )
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

