package com.mariomanzano.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mariomanzano.marvelcompose.MarvelApp
import com.mariomanzano.marvelcompose.data.model.Character
import com.mariomanzano.marvelcompose.data.model.Reference
import com.mariomanzano.marvelcompose.data.repositories.CharactersRepository

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(id: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(id)
    }

    characterState?.let { CharacterDetailScreen(it) }

}
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(character: Character) {
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        item{
            Header(character)
        }
        section(Icons.Default.Collections, "Series", character.references[2].references)
        section(Icons.Default.Event, "Events", character.references[1].references)
        section(Icons.Default.Book, "Comics", character.references[0].references)
        section(Icons.Default.Bookmark, "Stories", character.references[3].references)
    }
}

@ExperimentalMaterialApi
fun LazyListScope.section(icon: ImageVector, name: String, items : List<Reference>){
    item {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items){
        ListItem(
            icon = { Icon(
                icon,
                contentDescription = null
            )},
            text ={ Text(text = it.name)}
        )
    }
}

@ExperimentalCoilApi
@Composable
fun Header(character: Character) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(character.thumbnail),
            contentDescription = character.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalMaterialApi
@Preview (widthDp = 400, heightDp = 700)
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        1,
        "Iron Man",
        "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
        "",
        emptyList(),
        emptyList()
    )
    MarvelApp {
        CharacterDetailScreen(character)
    }
}