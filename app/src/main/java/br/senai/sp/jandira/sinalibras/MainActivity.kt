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
import com.google.firebase.FirebaseApp
import com.jakewharton.threetenabp.AndroidThreeTen

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
                        composable(route = "inicio") { Inicio(controleDeNavegacao) }
                        composable(route = "login") { Login(controleDeNavegacao) }
                        composable(route = "escolha"){ Escolha(controleDeNavegacao) }
                        composable(
                            "cadastro/{emailProfessor}",
                            arguments = listOf(navArgument("emailProfessor") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val emailProfessor = backStackEntry.arguments?.getString("emailProfessor") ?: ""
                            Cadastro(controleDeNavegacao, emailProfessor = emailProfessor)
                        }

                        composable(
                            "quiz?idFornecido={idFornecido}&emailFornecido={emailFornecido}",
                            arguments = listOf(
                                navArgument("idFornecido") { type = NavType.StringType },
                                navArgument("emailFornecido") { type = NavType.StringType },

                            )
                        ) { backStackEntry ->
                            val idFornecido = backStackEntry.arguments?.getString("idFornecido") ?: ""
                            val emailFornecido = backStackEntry.arguments?.getString("emailFornecido") ?: ""

                            Quiz(
                                controleDeNavegacao = controleDeNavegacao,
                                idFornecido = idFornecido,
                                emailFornecido = emailFornecido
                            )
                        }

                        composable(
                            "perfil?id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },

                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Perfil(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario
                            )
                        }
                        composable(
                            "configuracoes?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType;nullable=true },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento = backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

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

                        composable(
                            "sobreNos?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType;nullable=true },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento = backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

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

                        composable(
                            "editarPerfil?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType;nullable=true },
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento = backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

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

                        composable(
                            "editarSenha?id={id}&email={email}&nome={nome}&dataNascimento={dataNascimento}&fotoPerfil={fotoPerfil}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("dataNascimento") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType ;nullable=true},
                                navArgument("tipoUsuario") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val dataNascimento = backStackEntry.arguments?.getString("dataNascimento") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

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

                        composable(
                            "acerto?percentagem={porcentagem}&emailFornecido={emailFornecido}",
                            arguments = listOf(
                                navArgument("porcentagem") { type = NavType.StringType },
                                navArgument("emailFornecido") { type = NavType.StringType },

                                )
                        ) { backStackEntry ->
                            val porcentagem = backStackEntry.arguments?.getString("porcentagem") ?: ""
                            val emailFornecido = backStackEntry.arguments?.getString("emailFornecido") ?: ""

                            AcertoQuiz(
                                controleDeNavegacao = controleDeNavegacao,
                                porcentagem = porcentagem,
                                emailFornecido = emailFornecido
                            )
                        }

                        composable(
                            "erro/{porcentagem}",
                            arguments = listOf(navArgument("porcentagem") { type =
                                NavType.IntType })
                        ) { backStackEntry ->
                            ErroQuiz(controleDeNavegacao,porcentagem = backStackEntry.arguments?.getInt("porcentagem"))
                        }

                        composable(
                            "chatEspecifico?idDoOutroUsuario={idDoOutroUsuario}}&id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("idDoOutroUsuario") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("id") { type = NavType.StringType },


                                )
                        ) { backStackEntry ->
                            val idDoOutroUsuario = backStackEntry.arguments?.getString("idDoOutroUsuario") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val id = backStackEntry.arguments?.getString("id") ?: ""

                            ChatEspecifico(
                                controleDeNavegacao = controleDeNavegacao,
                                idDoOutroUsuario = idDoOutroUsuario,
                                id = id,
                                tipoUsuario = tipoUsuario
                            )
                        }
                        composable(
                            "video?idDoVideo={idDoVideo}}&id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("idDoVideo") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("id") { type = NavType.StringType },


                                )
                        ) { backStackEntry ->
                            val idDoVideo = backStackEntry.arguments?.getString("idDoVideo") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val id = backStackEntry.arguments?.getString("id") ?: ""

                            VideoInfo(
                                controleDeNavegacao = controleDeNavegacao,
                                idDoVideo = idDoVideo,
                                id = id,
                                tipoUsuario = tipoUsuario
                            )
                        }

                        composable(
                            "aulas?idModulo={idModulo}&id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("idModulo") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val idModulo = backStackEntry.arguments?.getString("idModulo") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Aulas(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                idModulo = idModulo,
                                tipoUsuario = tipoUsuario

                            )
                        }

                        composable(
                            "feed?id={id}&tipoUsuario={tipoUsuario}&fotoPerfil={fotoPerfil}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                                navArgument("fotoPerfil") { type = NavType.StringType;nullable=true},


                                )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""
                            val fotoPerfil = backStackEntry.arguments?.getString("fotoPerfil") ?: ""


                            Feed(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario,
                                fotoPerfil = fotoPerfil
                            )
                        }

                        composable(
                            "modulos?id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Modulos(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario

                            )
                        }
                        composable(
                            "chat?id={id}&tipoUsuario={tipoUsuario}",
                            arguments = listOf(
                                navArgument("id") { type = NavType.StringType },
                                navArgument("tipoUsuario") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: ""

                            Chat(
                                controleDeNavegacao = controleDeNavegacao,
                                id = id,
                                tipoUsuario = tipoUsuario
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

