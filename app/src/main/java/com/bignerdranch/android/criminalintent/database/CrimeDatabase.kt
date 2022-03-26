package com.bignerdranch.android.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.criminalintent.Crime


//аннотация сообщает о том что класс представляет базу данных
//аннотации требуется 2 параметра,список классов сущностей и версия бд
@Database(entities = [Crime::class], version = 1)
//явно добавляем конвертер
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase:RoomDatabase() {
    //приказ бд создать экземпляр DAO,Room сам сгенерирует реализацию
    abstract fun crimeDao():CrimeDao
}