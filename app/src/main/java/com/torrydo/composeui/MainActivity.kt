package com.torrydo.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.torrydo.composeui.sample.Login_screen_1
import com.torrydo.composeui.sample.Merchandise_screen_1
import com.torrydo.composeui.ui.theme.ComposeUITheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Merchandise_screen_1()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreen1() {
    ComposeUITheme {
        Login_screen_1()
    }
}