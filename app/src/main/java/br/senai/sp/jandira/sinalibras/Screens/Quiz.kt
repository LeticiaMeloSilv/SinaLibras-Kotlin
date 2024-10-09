package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Quiz(controleDeNavegacao:NavHostController) {
    controleDeNavegacao.navigate("acerto/${70}")

}