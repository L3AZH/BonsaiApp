package com.l3azh.bonsaiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.l3azh.bonsaiapp.Navigation.BonsaiNavHost
import com.l3azh.bonsaiapp.Service.PickAndCaptureImageService
import com.l3azh.bonsaiapp.ViewModel.AdminMainMenuViewModel
import com.l3azh.bonsaiapp.ViewModel.AdminTreeTypeViewModel
import com.l3azh.bonsaiapp.ViewModel.LoginViewModel
import com.l3azh.bonsaiapp.ViewModel.RegisterViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val loginViewModel:LoginViewModel by viewModels()
    val registerViewModel:RegisterViewModel by viewModels()
    val adminMainMenuViewModel:AdminMainMenuViewModel by viewModels()
    val adminTreeTypeViewModel:AdminTreeTypeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PickAndCaptureImageService.registerWithActivity(this)
        setContent {
            BonsaiAppTheme {
                // A surface container using the 'background' color from the theme
                val navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BonsaiNavHost(navHostController = navHostController, modifier = Modifier)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFirstScreen(){
    BonsaiAppTheme {
        // A surface container using the 'background' color from the theme
        val navHostController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            BonsaiNavHost(navHostController = navHostController, modifier = Modifier)
        }
    }
}

