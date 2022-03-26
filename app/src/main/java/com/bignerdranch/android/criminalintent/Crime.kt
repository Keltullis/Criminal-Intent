package com.bignerdranch.android.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


//сущность базы данных с первичным ключом, т.е целая таблица
//Каждая строка это отдельное преступление,а каждое свойство это столбец
@Entity
data class Crime(@PrimaryKey val id:UUID = UUID.randomUUID(),
                 var title:String = "",
                 var date:Date = Date(),
                 var isSolved:Boolean = false)