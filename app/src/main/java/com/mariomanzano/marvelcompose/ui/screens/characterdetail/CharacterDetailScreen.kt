package com.mariomanzano.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mariomanzano.marvelcompose.data.model.Character
import com.mariomanzano.marvelcompose.data.model.Reference
import com.mariomanzano.marvelcompose.data.repositories.CharactersRepository
import com.mariomanzano.marvelcompose.ui.navigation.AppBarIcon

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(id: Int, navController: NavController) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(id)
    }

    characterState?.let { CharacterDetailScreen(it) { navController.popBackStack() } }

}
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(character: Character, onUpClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text (character.title)},
                navigationIcon = { AppBarIcon(
                    imageVector = Icons.Default.ArrowBack,
                    onClick = onUpClick) },
                actions = {
                    AppBarOverflowMenu(urls = character.urls)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(character)
            }
            section(Icons.Default.Collections, "Series", character.references[2].references)
            section(Icons.Default.Event, "Events", character.references[1].references)
            section(Icons.Default.Book, "Comics", character.references[0].references)
            section(Icons.Default.Bookmark, "Stories", character.references[3].references)
        }
    }
}

@ExperimentalMaterialApi
fun LazyListScope.section(icon: ImageVector, name: String, items : List<Reference>){
    if (items.isEmpty()) return
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