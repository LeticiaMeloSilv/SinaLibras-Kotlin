package br.senai.sp.jandira.sin alibras.Screens

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
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultFeed
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.model.ResultProfessores
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feed(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String
) {
    val scrollState = rememberScrollState()

    var dados by remember {
        mutableStateOf(ResultFeed())
    }
    var focus by remember {
        mutableStateOf(false)
    }
    var funcionouState= remember{
        mutableStateOf(false)
    }
    val erroState=remember{
        mutableStateOf(false)
    }
    val pesquisaProfessor=remember{
        mutableStateOf(false)
    }
    var dadosPerfilProfessor by remember {
        mutableStateOf(ResultProfessores())
    }
    var pesquisaState = remember { mutableStateOf("") }

    val callFeed = RetrofitFactory().getPostagensService().getAllFeed()
    callFeed.enqueue(object : Callback<ResultFeed> {
        override fun onResponse(p0: Call<ResultFeed>, p1: Response<ResultFeed>) {
            val alunoResponse = p1.body()
            if (alunoResponse == null) {
                erroState.value=true
                Log.i("ERRO_MODULOS", p1.toString())
            } else {
                Log.i("ERRO_", alunoResponse.toString())
                funcionouState.value = true
                dados = alunoResponse
            }
        }

        override fun onFailure(p0: Call<ResultFeed>, p1: Throwable) {
            erroState.value=true

            Log.i("ERRO_PERFIL", p1.toString())
        }


    })
    if (!funcionouState.value && !erroState.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFC7E2FE), Color(0xFF345ADE))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logotipo SinaLibras",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "SinaLibras",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5)
                )

                Spacer(modifier = Modifier.height(32.dp))

                //isso q faz o circulo q roda
                CircularProgressIndicator(
                    color = Color(0xFF3F51B5),
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    } else if (funcionouState.value) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 110.dp, top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                )

                Image(
                    painter = rememberAsyncImagePainter(
                        model = fotoPerfil,
                        placeholder = painterResource(R.drawable.perfil),
                        error = painterResource(id = R.drawable.erro)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFA5D1FF), CircleShape)
                        .clickable {
                            controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                        .clip(CircleShape), // Aplica a forma circular
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFA5D1FF), shape = RoundedCornerShape(24.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Black,
                        modifier=Modifier.padding(start=10.dp).clickable{
                            funcionouState.value=false
                            val callProfessorById = RetrofitFactory().getUsuarioService().getPesquisarProfessor(pesquisaState.value)
                            callProfessorById.enqueue(object : Callback<ResultProfessores> {
                                override fun onResponse(p0: Call<ResultProfessores>, p1: Response<ResultProfessores>) {
                                    val professorResponse = p1.body()
                                    if (p1.isSuccessful) {
                                        if (professorResponse != null) {
                                            if(professorResponse.status_code!=404){
                                                pesquisaProfessor.value=true
                                                dadosPerfilProfessor=professorResponse
                                                funcionouState.value = true
                                            }else{
//aq vc coloca o pesquisar por video
                                            }
                                        }
                                    } else {

                                        Log.i("API Error", "Null response body")
                                    }
                                }

                                override fun onFailure(p0: Call<ResultProfessores>, p1: Throwable) {
                                    Log.i("ERRO_PERFIL", p1.toString())
                                }


                            })
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = pesquisaState.value,
                        onValueChange = { pesquisaState.value = it },
                        label={ Text(text = "Buscar...", color = Color.Black)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                funcionouState.value=false
                                val callProfessorById = RetrofitFactory().getUsuarioService().getPesquisarProfessor(pesquisaState.value)
                                callProfessorById.enqueue(object : Callback<ResultProfessores> {
                                    override fun onResponse(p0: Call<ResultProfessores>, p1: Response<ResultProfessores>) {
                                        val professorResponse = p1.body()
                                        if (p1.isSuccessful) {
                                            if (professorResponse != null) {
                                                if(professorResponse.status_code!=404){
                                                    pesquisaProfessor.value=true
                                                    dadosPerfilProfessor=professorResponse
                                                    funcionouState.value = true
                                                    Log.i("CALMA",professorResponse.toString())
                                                }else{
//aq vc coloca o pesquisar por video
                                                }
                                            }
                                        } else {

                                            Log.i("API Error", "Null response body")
                                        }
                                    }

                                    override fun onFailure(p0: Call<ResultProfessores>, p1: Throwable) {
                                        Log.i("ERRO_PERFIL", p1.toString())
                                    }


                                })
                            }
                        )
                    )
                }

//                Icon(
//                    imageVector = Icons.Filled.List,
//                    contentDescription = "Filtrar",
//                    tint = Color.Black,
//                    modifier = Modifier.size(24.dp)
//                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                if(!pesquisaProfessor.value){
                dados.feed?.forEach { feed ->

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF)),
                        modifier=Modifier.clickable{
                            if(feed.tipo=="postagem"){
if(feed.professor?.id_professor ?: 0==id.toLong()&&tipoUsuario=="Professor"){
    controleDeNavegacao.navigate("editarPostagem?idDaPostagem=${feed.id}&tipoUsuario=${tipoUsuario}&id=${id}&fotoPerfil=${fotoPerfil}&fotoCapa=${feed.foto}&texto=${feed.conteudo}")
}
                                else{
                                    controleDeNavegacao.navigate("video?idDoVideo=${feed.id}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                            }
                            else{
                                controleDeNavegacao.navigate("video?idDoVideo=${feed.id}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&idModulo=${feed.modulo?.id_modulo}&nomeModulo=${feed.modulo?.modulo}")
                            }
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Log.i("ERRO_", feed.professor?.foto_perfil.toString())
                            if (feed.professor != null) {
                                val painter: Painter =
                                    if (feed.professor.foto_perfil != "" && feed.professor.foto_perfil != "null" && feed.professor.foto_perfil != null && feed.professor.foto_perfil.isNotEmpty()) {
                                        rememberAsyncImagePainter(
                                            model = feed.professor.foto_perfil,
                                            placeholder = painterResource(R.drawable.perfil),
                                            error = painterResource(id = R.drawable.erro)
                                        )
                                    } else {
                                        painterResource(id = R.drawable.perfil)
                                    }

                                Image(
                                    painter = painter,
                                    contentDescription = "foto de usuario do professor que postou essa publicação",
                                    modifier = Modifier
                                        .background(Color(0xFFA5D1FF), CircleShape)
                                        .size(40.dp)
                                        .clip(CircleShape), // Aplica a forma circular
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = feed.professor.nome,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                painterResource(id = R.drawable.perfil)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .requiredHeightIn(min = 180.dp)
                                .background(Color(0xFF3F51B5), shape = RoundedCornerShape(16.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            var fotoPublicacao: Painter
                            if (feed.tipo == "postagem") {
                                fotoPublicacao =
                                    rememberAsyncImagePainter(
                                        model = feed.foto,
                                        error = painterResource(id = R.drawable.erro),
                                        contentScale = ContentScale.Crop
                                    )

                            } else {
                                fotoPublicacao =
                                    rememberAsyncImagePainter(
                                        model = feed.foto_capa,
                                        error = painterResource(id = R.drawable.erro),
                                        contentScale = ContentScale.Crop
                                    )
                            }
                                    Image(
                                        painter = fotoPublicacao,
                                        contentDescription = "foto da publicação",
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                        }
                        if (feed.tipo == "postagem") {
                            Text(
                                text = feed.conteudo,
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
            else{
                    Button(
                        onClick = {
                            pesquisaProfessor.value=false
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
                    Row{
                    dadosPerfilProfessor.professor?.forEach{professor->
                        val painter: Painter =
                            if (professor.foto_perfil != "" && professor.foto_perfil != "null" && professor.foto_perfil != null && professor.foto_perfil.isNotEmpty()) {
                                rememberAsyncImagePainter(
                                    model = professor.foto_perfil,
                                    placeholder = painterResource(R.drawable.perfil),
                                    error = painterResource(id = R.drawable.erro)
                                )
                            } else {
                                painterResource(id = R.drawable.perfil)
                            }
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,modifier=Modifier.padding(start=16.dp,top=16.dp).clickable{
                                controleDeNavegacao.navigate(
                                    "outroPerfil?id=${id}&idOutroUsuario=${
                                        professor.id_professor
                                    }&tipoUsuario=${tipoUsuario}&tipoOutroUsuario=professor&fotoPerfil=${fotoPerfil}")
                            }){
                        Image(
                            painter = painter,
                            contentDescription = "foto de usuario do professor que postou essa publicação",
                            modifier = Modifier
                                .background(Color(0xFFA5D1FF), CircleShape)
                                .size(100.dp)
                                .clip(CircleShape), // Aplica a forma circular
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = professor.nome,
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold)
                        }
                    }}

            }}
        }
        if (focus) {
            Box(modifier=Modifier.fillMaxSize().background(color=Color(0x68090A1E))){
            Column(
                modifier = Modifier
                    .width(258.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .align(Alignment.BottomCenter)
                    .padding(start=16.dp,end=16.dp,top=16.dp, bottom = 105.dp)
            ) {
                Button(
                    onClick = {
                        focus = false
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFC7E2FE))
                        .border(1.dp, Color.Black)
                        .clickable{
                            controleDeNavegacao.navigate("postarVideo?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                        }
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Adicionar aula",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.videoaula),
                        contentDescription = "adicionar aula",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFC7E2FE))
                        .border(1.dp, Color.Black)
                        .clickable{
                            controleDeNavegacao.navigate("postarPostagem?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                        }
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Adicionar post",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.postagem),
                        contentDescription = "adicionar postagem",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }}
        }

        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            colors = CardColors(
                containerColor = Color(0xFFA5D1FF),
                contentColor = Color(0xFFA5D1FF),
                disabledContentColor = Color(0xFFA5D1FF),
                disabledContainerColor = Color(0xFFA5D1FF)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 38.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { controleDeNavegacao.navigate("chat?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat Icon",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Chat",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil_icone),
                        contentDescription = "icone de perfil",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Perfil",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)

                    )
                }

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFA5D1FF))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    if (tipoUsuario == "aluno") {
                        Image(
                            painter = painterResource(
                                id = R.drawable.rank
                            ),
                            contentDescription = "Profile Icon",
                            modifier = Modifier
                                .size(45.dp)
                                .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") },
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Image(
                            painter = painterResource(
                                id = R.drawable.mais
                            ),
                            contentDescription = "Profile Icon",
                            modifier = Modifier
                                .size(45.dp)
                                .clickable { focus = true },
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            controleDeNavegacao.navigate("modulos?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.videos),
                        contentDescription = "Classes Icon",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Aulas",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.feed),
                        contentDescription = "Menu Icon",
                        modifier = Modifier
                            .size(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Feed",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .background(color = Color(0xff3459DE))
                    )
                }
            }

        }
    }}
    else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD0E6FF))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
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
                painter = painterResource(id = R.drawable.erro),
                contentDescription = "logo",
                modifier = Modifier

            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "ERRO!!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "mande uma\nmensagem para o\ntime de suporte",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }

}

