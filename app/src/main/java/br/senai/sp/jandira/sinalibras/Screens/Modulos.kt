package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R

@Composable
fun Modulos(controleDeNavegacao: NavHostController, id: String, tipoUsuario: String) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFC7E2FE))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Header(userName = "Gabriel")

        // Video list
        VideoList()
    }
}

@Composable
fun Header(userName: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        IconButton(onClick = { /* Ação de voltar */ }) {
            Icon(modifier = Modifier.padding(vertical = 7.dp),
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "BackArrow",
                tint = Color.Black)
        }

        Text(
            text = "Saudações",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )


    }
}

@Composable
fun VideoList() {
    LazyColumn {
        items(4) { index ->
            VideoCard(
                title = "LIBRAS",
                duration = "13:20",
                professor = "Professor: Joyce Godoy",
                thumbnailRes = R.drawable.logo_grande
            )
        }
    }
}

@Composable
fun VideoCard(title: String, duration: String, professor: String, thumbnailRes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)),
        modifier = Modifier

            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { /* Ação ao clicar no vídeo */ }
    ) {
        Column {
            // Video thumbnail
            Image(
                painter = painterResource(id = thumbnailRes),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            // Video information
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = professor, fontSize = 14.sp)
            }
        }
    }
}