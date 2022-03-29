package com.bignerdranch.android.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.criminalintent.Crime
import java.util.*


//интерфейс для доступа к данным,содержит функции для каждой операции с бд
@Dao
interface CrimeDao {
    //Query указывает, что функции предназначены для извлечения информации из базы данных
    //а не вставки, обновления или удаления
    //если DAO возвращает LiveData то Room автоматически выполнит запросы в фоновом режиме
    //а затем обернёт в LiveData
    @Query("SELECT * FROM crime")
    fun getCrimes():LiveData<List<Crime>>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id:UUID):LiveData<Crime?>
}