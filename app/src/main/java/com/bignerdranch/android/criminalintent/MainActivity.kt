package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //что быолучить экземпляр CrimeFragment от FragmentManager,
        //нужно запросить его по идентификатору контейнерного представления FrameLayout
        //если фрагмент уже есть,то мы получим его здесь,а если нет,то создадим ниже
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null){
            val fragment = CrimeFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }
    }
}
//Фрагменты размещаются во фреймах.