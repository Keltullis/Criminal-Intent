package com.bignerdranch.android.criminalintent

import android.app.Application

//Подкласс Application вызывается системой когда приложение впервые загружается в память
//Это подходящее место для разовой инициализации шаблона репозитория
class CriminalIntentApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        //передаём экземпляр приложения в качестве объекта context
        CrimeRepository.initialize(this)
    }
}