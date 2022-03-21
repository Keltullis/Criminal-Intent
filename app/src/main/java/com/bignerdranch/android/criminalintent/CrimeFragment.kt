package com.bignerdranch.android.criminalintent

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.text.DateFormat
import java.text.SimpleDateFormat

class CrimeFragment:Fragment() {
    //Здесь происходит объявление переменных для дальнейшей инициализации
    private lateinit var crime:Crime
    private lateinit var titleField:EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    //Здесь происходит настройка фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime= Crime()
    }

    //Здесь происходит создание и заполнение представления фрагмента
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime,container,false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply {
            //text = crime.date.toString()
            text = android.text.format.DateFormat.format("EEEE, MMM dd, yyyy.", crime.date)
            isEnabled = false
        }
        return view
    }

    //Устанавливаем слушатели
    //Если установить слушатель в onStart то слушатель подключится после восстановления состояния виджета
    override fun onStart() {
        super.onStart()


        val titleWatcher = object :TextWatcher{
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Это пространство оставлено пустым специально
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                // И это
            }
        }

        solvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }

        titleField.addTextChangedListener(titleWatcher)
    }
}
