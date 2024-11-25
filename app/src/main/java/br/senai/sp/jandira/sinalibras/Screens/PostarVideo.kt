package br.senai.sp.jandira.sinalibras.Screens


import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultNivel
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
 var linkUrl=""
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostVideo(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    getContent: () -> Unit,
    initialImageUri: Uri?
) {
    val currentDate: LocalDate = LocalDate.now()

    var mensagemErroState = remember {
        mutableStateOf("")
    }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var fotoState = remember { mutableStateOf("") }
    var descricaoState = remember { mutableStateOf("") }
    var tituloState = remember { mutableStateOf("") }
    var idModuloEscolhidoState = remember { mutableStateOf("") }

    var idNivelEscolhidoState = remember { mutableStateOf("") }


    var dadosModulos by remember {
        mutableStateOf(ResultModulo())
    }
    var dadosNiveis by remember {
        mutableStateOf(ResultNivel())
    }
    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        Log.i("upload",initialImageUri.toString())
        imageUri?.let { uploadVideo(it) }
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
            .padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
                AndroidView(
                    factory = { context ->
                        VideoView(context).apply {
                            setVideoURI(imageUri)
                            start()
                        }
                    },
                    modifier = Modifier
                        .size(width = 240.dp, height = 170.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.upload),
                    contentDescription = "campo inserir video",
                    modifier = Modifier
                        .size(width = 240.dp, height = 170.dp)
                        .clickable {
                            getContent()
                        },
                    contentScale = ContentScale.Crop
                )
            }



            Spacer(modifier = Modifier.height(12.dp))

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
                Log.i("upload",dadosModulos.modulo.toString())
                dadosModulos.modulo?.forEach { item ->
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
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

            Spacer(modifier = Modifier.height(100.dp))
            Text(text = mensagemErroState.value, color = Color.Red)
        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),

            colors = CardColors(
                containerColor = Color(0xFFD0E6FF),
                contentColor = Color(0xFFD0E6FF),
                disabledContentColor = Color(0xFFD0E6FF),
                disabledContainerColor = Color(0xFFD0E6FF)
            )
        ) {
            Button(
                onClick = {
Log.i("CARALHO",id)
                    if(tituloState.value==""|| descricaoState.value==""||idModuloEscolhidoState.value==""||idNivelEscolhidoState.value==""||id==""||linkUrl==""||currentDate.toString()==""){
                        mensagemErroState.value = "Existem campos requiridos que não foram preenchidos"

                    }
                    val callUsuarios = RetrofitFactory()
                        .getPostagensService().setSalvarVideoAula(
                            videoAula = VideoAula(
                                titulo = tituloState.value,
                                duracao = "00:10:00",
                                descricao = descricaoState.value,
                                foto_capa = "link",
                                id_modulo = idModuloEscolhidoState.value.toInt(),
                                id_nivel = idNivelEscolhidoState.value.toInt(),
                                id_professor = id.toLong(),
                                url_video = linkUrl,
                                data = currentDate.toString()
                            )
                        )
                    callUsuarios.enqueue(object : Callback<ResultVideo> {
                        override fun onResponse(
                            p0: Call<ResultVideo>,
                            p1: Response<ResultVideo>
                        ) {
                            val usuarioSalvo = p1.body()
                            Log.i("aaaaa",usuarioSalvo.toString())
                            Log.i("aaaaa",p0.toString())


                            if (p1.isSuccessful) {
                                if (usuarioSalvo != null) {
                                    linkUrl=""
                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                                else {
                                    linkUrl=""
                                    mensagemErroState.value = "Ocorreu um erro por parte do Servidor, tente novamente mais tarde"

                                }

                            } else {
                                if(usuarioSalvo!=null){
                                    linkUrl=""
                                    mensagemErroState.value = usuarioSalvo.message.toString()}
                                else{
                                    linkUrl=""
                                    mensagemErroState.value="Ocorreu um erro, verifique se preencheu todos os campos"}

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
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "POSTAR", fontWeight = FontWeight.Bold)
            }
        }

    }
}


fun uploadVideo(fileUri: Uri) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("videos/${fileUri.lastPathSegment}")
    fileRef.putFile(fileUri)
        .addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener { uri ->
                Log.i("UPLOAD", "URL de download: $uri")
                linkUrl = uri.toString()
            }
        }
        .addOnFailureListener { exception ->
            linkUrl = ""
            Log.i("UPLOAD", "Falha no upload: ${exception.message}")
        }
}
