package com.mariomanzano.marvelcompose.ui.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mariomanzano.marvelcompose.MarvelApp
import com.mariomanzano.marvelcompose.data.model.Character

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(characters: List<Character>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(characters){
            CharacterItem(it)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterItem(character: Character) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Preview
@Composable
fun CharacterScreenPrev() {
    val characters = (1..10).map {
        Character(
            it,
            "Name $it",
            "Description",
            "https://via.placeholder.com/150x225/FFFF00/000000?text=name$it"
        )
    }
    MarvelApp {
        CharactersScreen(characters = characters)
    }
}