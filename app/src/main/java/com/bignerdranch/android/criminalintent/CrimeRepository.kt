package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import java.util.*

private const val DATABASE_NAME = "crime-database"

//Это репозиторий,он инкапсулирует логику для доступа к данным из одного источника или совокупности источников
class CrimeRepository private constructor(context:Context){

    //databaseBuilder создаёт конкретную реализацию абстрактного класса CrimeDatabase
    //бд обращается к файловой системе,поэтому нужен context
    //сам класс бд
    //имя файла бд которую создаст Room
    private val database:CrimeDatabase = Room.databaseBuilder(context.applicationContext,CrimeDatabase::class.java, DATABASE_NAME).build()

    private val crimeDao = database.crimeDao()

    fun getCrimes():List<Crime> = crimeDao.getCrimes()

    fun getCrime(id:UUID):Crime? = crimeDao.getCrime(id)
    //Room обеспечивает реализацию запросов в DAO,мы обращаемся к этим реализациям из репозитория

    companion object{
        private var INSTANCE:CrimeRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get():CrimeRepository{
            return INSTANCE?:throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}