package com.ifts.clubdeportivogrupo9.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.navigation.AppScreens
import com.ifts.clubdeportivogrupo9.screens.components.EventDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(
    navController: NavController,
    state: LoginState,
    onLogin: (String, String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onDismissDialog: () -> Unit
) {
    Scaffold( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
            Text(text = "Iniciar sesión")
            },
//            navigationIcon = {
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "Atras",
//                        modifier = Modifier.clickable {
//                            navController.popBackStack()
//                        }
//                    )
//                }
//            }
        )
    }

    ) {
        LoginBodyContent(navController, onLogin, state, onDismissDialog)
//        if(state: errorMessage != null){
//            EventDialog(
//                errorMessage = state.errorMessage,
//                onDismiss = onDismissDialog
//            )
//    }
    }
}

@Composable
fun LoginBodyContent(navController: NavController, onLogin: (String, String) -> Unit, state: LoginState, onDismissDialog: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val userName = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisibility = remember { mutableStateOf(false) }
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
//        var RememberUserCheck = remember { mutableStateOf(false) }

        //  Nombre de usuario
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.75f),
            value = userName.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                userName.value = it
            },
            label = { Text(text = "Usuario") }
        )

        //  Contraseña
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.75f),
            value = password.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
//                    focusManager.clearFocus()
                    onLogin(userName.value, password.value)
                }
            ),
            trailingIcon = {
                           IconButton(
                               onClick = {
                                   passwordVisibility.value = !passwordVisibility.value
                               }) {
                                Icon(
                                    imageVector = if(passwordVisibility.value) {
                                        Icons.Filled.Visibility
                                    } else {
                                        Icons.Filled.VisibilityOff
                                    },
                                    contentDescription = ""
                                )
                           }
            },
            visualTransformation = if(passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            onValueChange = {
                password.value = it
            },
            label = { Text(text = "Contraseña") }
        )
//        Row(
//            modifier = Modifier.fillMaxWidth(0.75f),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Checkbox(
//                checked = RememberUserCheck,
//                onCheckedChange = { RememberUserCheck = it }
//            )
//            Text(
//                "Recordar usuario"
//            )
//        }
        Spacer(modifier = Modifier.size(40.dp))
        Button(
            onClick = {
                if (userName.value.isBlank() || password.value.isBlank() ){
                    Toast.makeText(context, "Ingrese usuario y contraseña", Toast.LENGTH_LONG).show()
                }else{
                    onLogin(userName.value, password.value)
//                    navController.navigate(route = AppScreens.MenuPrincipal.route)

                }

        },) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    //.width(200.dp)
                    .fillMaxWidth(0.6f),
            )
//            displayProgressBar = state.displayProgressBar
        }
    }
    if (state.errorMessage != null){
        EventDialog(
            errorMessage = state.errorMessage,
            onDismiss = onDismissDialog
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrarSocio()
}*/