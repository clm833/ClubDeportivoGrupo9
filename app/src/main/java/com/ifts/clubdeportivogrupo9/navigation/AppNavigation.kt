package com.ifts.clubdeportivogrupo9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ifts.clubdeportivogrupo9.MainViewModel
import com.ifts.clubdeportivogrupo9.screens.BuscarCredencial
import com.ifts.clubdeportivogrupo9.screens.MenuPrincipal
import com.ifts.clubdeportivogrupo9.screens.RegistrarSocio
import com.ifts.clubdeportivogrupo9.screens.VerCredencial
import com.ifts.clubdeportivogrupo9.screens.CobrarCuota
import com.ifts.clubdeportivogrupo9.screens.VerCuotasSocio
import com.ifts.clubdeportivogrupo9.screens.login.Login
import com.ifts.clubdeportivogrupo9.screens.VerCuotasVencidas.VerCuotasVencidas
import com.ifts.clubdeportivogrupo9.screens.login.LoginViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Login.route){
        addLogin(navController)
//        composable(route = AppScreens.Login.route) {
//            val viewModel: LoginViewModel = LoginViewModel()
//            Login(
//                    navController,
//                    state = viewModel.state.value,
//                    onLogin = viewModel::login,
//                    onNavigateToRegister = {},
//                    onDismissDialog = viewModel::hideErrorDialog
//                )
//        }
        composable(route = AppScreens.MenuPrincipal.route) {
            MenuPrincipal(navController)
        }
        composable(route = AppScreens.RegistrarSocio.route){
            RegistrarSocio(navController, viewModel)
        }
        composable(route = AppScreens.BuscarCredencial.route){
            BuscarCredencial(navController, viewModel)
        }
        composable(route = AppScreens.VerCredencial.route){
            VerCredencial(navController, viewModel)
        }
        composable(route = AppScreens.CobrarCuota.route){
            CobrarCuota(navController, viewModel)
        }
        composable(route = AppScreens.VerCuotasVencidas.route){
            VerCuotasVencidas(navController, viewModel)
        }
        composable(route = AppScreens.VerCuotasSocio.route){
            VerCuotasSocio(navController, viewModel)
        }
    }

}

fun NavGraphBuilder.addLogin(
    navController: NavHostController
) {
        composable(route = AppScreens.Login.route) {

            val viewModel: LoginViewModel = hiltViewModel()
            val email = viewModel.state.value.email
            val password = viewModel.state.value.password

            if (viewModel.state.value.successLogin){
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = AppScreens.MenuPrincipal.route){
                        popUpTo(AppScreens.Login.route){
                            inclusive = true
                        }
                    }
                }
            } else {
                Login(
                    navController,
                    state = viewModel.state.value,
                    onLogin = viewModel::login,
                    onNavigateToRegister = {},
                    onDismissDialog = viewModel::hideErrorDialog
                )
            }
        }
}