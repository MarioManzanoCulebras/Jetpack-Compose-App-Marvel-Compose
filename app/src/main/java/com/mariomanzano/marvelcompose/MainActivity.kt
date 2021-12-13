package com.mariomanzano.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.mariomanzano.marvelcompose.ui.navigation.Navigation
import com.mariomanzano.marvelcompose.ui.theme.MarvelComposeTheme

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApp {
                Navigation()
            }
        }
    }
}

@Composable
fun MarvelApp(content: @Composable ()-> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}