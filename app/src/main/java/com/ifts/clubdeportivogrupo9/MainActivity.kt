/*
********************* GRUPO 9 *********************
*
*    Integrantes:
*               Natalia Rodríguez
*               Cristian Muliterno
*
* *************************************************
*/

package com.ifts.clubdeportivogrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.ifts.clubdeportivogrupo9.data.ClubDataBase
import com.ifts.clubdeportivogrupo9.data.CuotaMensualRepository
import com.ifts.clubdeportivogrupo9.data.SocioRepository
import com.ifts.clubdeportivogrupo9.navigation.AppNavigation
import com.ifts.clubdeportivogrupo9.ui.theme.ClubDeportivoGrupo9Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, ClubDataBase::class.java, "socios_db").build()
        val dao = db.dao
        val repository = SocioRepository(dao)
        val cuotaMensualRepository = CuotaMensualRepository(dao)

        val viewModel = MainViewModel(repository, cuotaMensualRepository)
        enableEdgeToEdge()
        setContent {
            ClubDeportivoGrupo9Theme {
                AppNavigation(viewModel)
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClubDeportivoGrupo9Theme {
        AppNavigation(viewModel)
    }
}
*/