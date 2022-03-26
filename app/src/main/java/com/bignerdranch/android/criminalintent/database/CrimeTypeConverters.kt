package com.bignerdranch.android.criminalintent.database

import androidx.room.TypeConverter
import java.util.*


//конвертер преобразует сложные типы в примитивные для сохранения в бд

class CrimeTypeConverters {

    //Конвертируем Date,в базу и обратно
    @TypeConverter
    fun fromDate(date: Date?):Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch:Long?):Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    //Конвертируем UUID,в базу и обратно
    @TypeConverter
    fun fromUUID(uuid: UUID?):String?{
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid:String?):UUID?{
        return UUID.fromString(uuid)
    }

}