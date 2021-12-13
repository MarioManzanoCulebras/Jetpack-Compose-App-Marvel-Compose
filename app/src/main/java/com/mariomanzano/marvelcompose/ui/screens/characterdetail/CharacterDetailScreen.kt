package com.mariomanzano.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mariomanzano.marvelcompose.data.model.Character
import com.mariomanzano.marvelcompose.data.repositories.CharactersRepository


@Composable
fun CharacterDetailScreen(id: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(id)
    }

    characterState?.let { CharacterDetailScreen(it) }

}
@Composable
fun CharacterDetailScreen(character: Character) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
            Text(text = character.title)
    }
}