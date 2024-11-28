package br.senai.sp.jandira.sinalibras.Screens


import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Postagem
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultNivel
import br.senai.sp.jandira.sinalibras.model.ResultPostagem
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPostagem(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    idDaPostagem: String,
    texto: String,
    getContent: () -> Unit,
    initialImageUri: Uri?,
) {
    var focusTela = remember {
        mutableStateOf(
            false
        )
    }

    var mensagemErroState = remember {
        mutableStateOf("")
    }
    val currentDate: LocalDate = LocalDate.now()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var fotoState = remember { mutableStateOf("") }
    var tituloState = remember { mutableStateOf(texto) }


    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        imageUri?.let { uploadFile(it) }
        fotoState.value = imageUri.toString()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(horizontal = 10.dp, vertical = 0.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
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


                Image(
                    painter = painterResource(id = R.drawable.logo_grande),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
            }

            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Foto selecionada",
                    modifier = Modifier
                        .height(150.dp)
                        .clickable { getContent() },
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.upload),
                    contentDescription = "Campo inserir foto",
                    modifier = Modifier
                        .height(150.dp)
                        .clickable { getContent() },
                    contentScale = ContentScale.Crop
                )
            }

            Text(text = mensagemErroState.value, color = Color.Red)

            Text(
                text = "Conteúdo:",

                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = tituloState.value,
                onValueChange = { tituloState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(alignment = Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val callUsuarios = RetrofitFactory()
                        .getPostagensService().setAtualizarPostagem(
                            postagem = Postagem(
                                texto = tituloState.value,
                                foto_postagem = link,
                                id_professor = id.toLong(),
                                data = currentDate.toString(),
                            ),
                            id = idDaPostagem.toInt()
                        )
                    callUsuarios.enqueue(object : Callback<ResultPostagem> {
                        override fun onResponse(
                            p0: Call<ResultPostagem>,
                            p1: Response<ResultPostagem>
                        ) {
                            val usuarioSalvo = p1.body()
                            Log.i("aaaaa", usuarioSalvo.toString())

                            if (p1.isSuccessful) {
                                if (usuarioSalvo != null) {
                                    linkUrl = ""

                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                } else {
                                    linkUrl = ""

                                    mensagemErroState.value =
                                        "Ocorreu um erro por servidor, tente novamente mais tarde"
                                }

                            } else {
                                linkUrl = ""
                                if (usuarioSalvo != null)
                                    mensagemErroState.value = usuarioSalvo.message.toString()
                                else
                                    mensagemErroState.value =
                                        "Ocorreu um erro, verifique se preencheu todos os campos"

                            }
                        }


                        override fun onFailure(
                            p0: Call<ResultPostagem>,
                            p1: Throwable
                        ) {
                            Log.i("aaaaa", p1.toString())
                            linkUrl = ""

                            mensagemErroState.value =
                                "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                        }

                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B5EDF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(135.dp)
                    .height(48.dp)
            ) {
                Text(text = "EDITAR", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    focusTela.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC44339),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(135.dp)
                    .height(48.dp)
            ) {
                Text(text = "EXCLUIR", fontWeight = FontWeight.Bold)
            }
        }

    }
    if (focusTela.value) {
        Column(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .background(color = Color(0x663A3D46))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xffffffff))
                    .size(height = 600.dp, width = 300.dp)
                    .padding(23.dp)
                    .border(
                        width = 0.dp,
                        shape = RoundedCornerShape(size = 16.dp),
                        color = Color.Transparent

                    )
            ) {
                Button(
                    onClick = {
                        focusTela.value = false
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
                Text(
                    text = "Tem certeza de que deseja excluir essa postagem?",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Essa ação não pode ser revertida",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val callDellAluno = RetrofitFactory().getPostagensService()
                                .setDellPostagem(idDaPostagem.toInt())
                            callDellAluno.enqueue(object : Callback<ResultPostagem> {
                                override fun onResponse(
                                    p0: Call<ResultPostagem>,
                                    p1: Response<ResultPostagem>
                                ) {
                                    val alunoResponse = p1.body()
                                    if (p1.isSuccessful) {
                                        imageUri = null
                                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                    } else {
                                        if (alunoResponse != null) {
                                            mensagemErroState.value =
                                                alunoResponse.message.toString()
                                        }
                                        Log.i("CALMA", alunoResponse?.message!!.toString())
                                    }
                                }

                                override fun onFailure(
                                    p0: Call<ResultPostagem>,
                                    p1: Throwable
                                ) {
                                    mensagemErroState.value =
                                        "Verifique se esta conectado a Internet ou tente novamente mais tarde"
                                    Log.i("ERRO_DELETAR_PERFIL", p1.toString())
                                }
                            })
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(size = 32.dp),
                                color = Color(0xff081F5C)
                            )
                    ) {
                        Text(
                            text = "Sim",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center
                        )
                    }
                    Button(
                        onClick = {
                            focusTela.value = false
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                    ) {
                        Text(
                            text = "Não",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }

}

