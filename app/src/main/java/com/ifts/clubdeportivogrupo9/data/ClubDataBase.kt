package com.ifts.clubdeportivogrupo9.data

import androidx.room.Database
import androidx.room.RoomDatabase
//import com.ifts.clubdeportivogrupo9.data.CuotaMensualEntity

@Database(
    entities = [
        SocioEntity::class,
        CuotaMensualEntity::class
    ],
    version = 1
)
abstract class ClubDataBase : RoomDatabase() {
    abstract val dao: SocioDao

}