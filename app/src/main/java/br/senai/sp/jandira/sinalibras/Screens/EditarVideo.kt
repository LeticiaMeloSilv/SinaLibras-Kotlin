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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultNivel
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarVideo(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    idDoVideo: String,
    idModulo: String,
    fotoCapa: String,
    duracao: String,
    url: String,
    titulo: String,
    idNivel: String,
    getContent: () -> Unit,
    initialImageUri: Uri?,
    descricao: String
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
    var tituloState = remember { mutableStateOf(titulo) }
    var descricaoState = remember { mutableStateOf(descricao) }
    var idModuloEscolhidoState = remember { mutableStateOf(idModulo) }

    var idNivelEscolhidoState = remember { mutableStateOf(idNivel) }


    var dadosModulos by remember {
        mutableStateOf(ResultModulo())
    }
    var dadosNiveis by remember {
        mutableStateOf(ResultNivel())
    }
    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        imageUri?.let { uploadFile(it) }
        fotoState.value = imageUri.toString()

        val callModulos = RetrofitFactory().getPostagensService().getAllModulos()
        callModulos.enqueue(object : Callback<ResultModulo> {
            override fun onResponse(p0: Call<ResultModulo>, p1: Response<ResultModulo>) {
                val alunoResponse = p1.body()
                if (alunoResponse == null) {
                    Log.i("ERRO_MODULOS", p1.toString())
                } else {
                    Log.i("TAG", alunoResponse.toString())
                    //funcionouState = true
                    dadosModulos = alunoResponse
                }
            }

            override fun onFailure(p0: Call<ResultModulo>, p1: Throwable) {
                Log.i("ERRO_PERFIL", p1.toString())
            }


        })

        val callNiveis = RetrofitFactory().getPostagensService().getAllNiveis()
        callNiveis.enqueue(object : Callback<ResultNivel> {
            override fun onResponse(p0: Call<ResultNivel>, p1: Response<ResultNivel>) {
                val alunoResponse = p1.body()
                if (alunoResponse == null) {
                    Log.i("ERRO_MODULOS", p1.toString())
                } else {
                    Log.i("TAG", alunoResponse.toString())
                    //funcionouState = true
                    dadosNiveis = alunoResponse
                }
            }

            override fun onFailure(p0: Call<ResultNivel>, p1: Throwable) {
                Log.i("ERRO_PERFIL", p1.toString())
            }


        })
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(horizontal = 10.dp, vertical = 0.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier=Modifier
                .padding(bottom = 20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        imageUri=null
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
                text = "Titulo do video:",

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
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Descrição do video:",

                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = descricaoState.value,
                onValueChange = { descricaoState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Nível",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
            )
            Row(
                modifier = Modifier.align(Alignment.Start).fillMaxWidth()
            ) {
                Log.i("aaaaa",dadosNiveis.toString())
                dadosNiveis.niveis?.forEach { nivel ->
                    Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Checkbox(
                            checked = idNivelEscolhidoState.value == nivel.id_nivel.toString(),
                            onCheckedChange = {
                                if (idNivelEscolhidoState.value == nivel.id_nivel.toString()) {
                                    idNivelEscolhidoState.value = ""
                                } else {
                                    idNivelEscolhidoState.value = nivel.id_nivel.toString()
                                }
                            },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                        )
                        Text(text = nivel.nivel, color = Color.Black)

                    } }
            }

            Text(
                text = "Módulo",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),

                )
            Column(
                modifier = Modifier.verticalScroll(scrollState).align(Alignment.Start)
            ) {
                dadosModulos.modulo?.forEach { item ->
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Log.i("calma", idModuloEscolhidoState.toString())
                        Checkbox(
                                    checked = idModuloEscolhidoState.value == item.id_modulo.toString(),
                            onCheckedChange = {
                                if (idModuloEscolhidoState.value == item.id_modulo.toString()) {
                                    // Desmarca o checkbox se já estiver selecionado
                                    idModuloEscolhidoState.value = ""
                                } else {
                                    // Marca o checkbox e atualiza o ID
                                    idModuloEscolhidoState.value = item.id_modulo.toString()
                                }
                            },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                        )
                        Text(text = item.modulo, color = Color.Black)

                    }
                }

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(alignment=Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    Log.i("aaaaa",tituloState.value)
                    Log.i("aaaaa",idModuloEscolhidoState.value)
                    Log.i("aaaaa",idNivelEscolhidoState.value)
                    Log.i("aaaaa",link)
val duracaoCerta=duracao.substring(11, 19)
                    val callUsuarios = RetrofitFactory()
                        .getPostagensService().setAtualizarVideoAula(
                            videoAula = VideoAula(
                                titulo = tituloState.value,
                                duracao = duracaoCerta,
                                foto_capa = link,
                                id_modulo = idModuloEscolhidoState.value.toInt(),
                                id_nivel = idNivelEscolhidoState.value.toInt(),
                                id_professor = id.toLong(),
                                url_video = url,
                                data=currentDate.toString(),
                                descricao=descricaoState.value
                            ),
                            id=idDoVideo.toInt()
                        )
                    callUsuarios.enqueue(object : Callback<ResultVideo> {
                        override fun onResponse(
                            p0: Call<ResultVideo>,
                            p1: Response<ResultVideo>
                        ) {
                            val usuarioSalvo = p1.body()
                            Log.i("aaaaa",usuarioSalvo.toString())

                            if (p1.isSuccessful) {
                                if (usuarioSalvo != null) {
                                    linkUrl=""

                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                                else{
                                    linkUrl=""

                                    mensagemErroState.value = "Ocorreu um erro por servidor, tente novamente mais tarde"
                                }

                            } else {
                                linkUrl=""
if(usuarioSalvo!=null)
                                mensagemErroState.value = usuarioSalvo.message.toString()
                                else
                                    mensagemErroState.value="Ocorreu um erro, verifique se preencheu todos os campos"

                            }
                        }


                        override fun onFailure(
                            p0: Call<ResultVideo>,
                            p1: Throwable
                        ) {
                            Log.i("aaaaa", p1.toString())
                            linkUrl=""

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
                    focusTela.value=true
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
                    text = "Tem certeza de que deseja excluir o video ${titulo}?",
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
                Row (
                    modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = {
                            val callDellAluno = RetrofitFactory().getPostagensService()
                                .setDellVideo(idDoVideo.toInt())
                            callDellAluno.enqueue(object : Callback<ResultVideo> {
                                override fun onResponse(
                                    p0: Call<ResultVideo>,
                                    p1: Response<ResultVideo>
                                ) {
                                    val alunoResponse = p1.body()
                                    if (p1.isSuccessful) {
                                        imageUri=null
                                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                    } else {
                                        if (alunoResponse != null) {
                                            mensagemErroState.value = alunoResponse.message.toString()
                                        }
                                        Log.i("CALMA", alunoResponse?.message!!.toString())
                                    }
                                }

                                override fun onFailure(
                                    p0: Call<ResultVideo>,
                                    p1: Throwable
                                ) {
                                    mensagemErroState.value =
                                        "Verifique se esta conectado a Internet ou tente novamente mais tarde"
                                    Log.i("ERRO_DELETAR_PERFIL", p1.toString())
                                }
                            })                            },
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
                        },                            colors = ButtonColors(
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

