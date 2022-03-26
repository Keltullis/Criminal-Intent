package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel:ViewModel() {
    //получаем ссылку на шаблон репозитория
    private val crimeRepository = CrimeRepository.get()

    val crimes = crimeRepository.getCrimes()
}