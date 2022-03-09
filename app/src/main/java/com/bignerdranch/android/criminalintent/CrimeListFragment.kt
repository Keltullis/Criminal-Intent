package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

class CrimeListFragment:Fragment() {

    private lateinit var crimeRecyclerView:RecyclerView
    private var adapter:CrimeAdapter? = null

    private val crimeListViewModel:CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Надуваем xml для фрагмента
        val view = inflater.inflate(R.layout.fragment_crime_list,container,false)

        //Задаём LayoutManager для RecyclerView что бы он смог отображать объекты на экране
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI(){
        val crimes = crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    //ViewHolder - класс обёртка для представление элемента
    //RecycledView не создаёт view сам по себе,он создаёт ViewHolder которые выводят свои itemView
    private abstract class CrimeHolder(view: View) : RecyclerView.ViewHolder(view) {
        var crime = Crime()
        val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
    }

    //Холдер для обыденных преступлений
    private inner class NormalCrimeHolder(view: View):CrimeHolder(view),View.OnClickListener{
        //присваиваем слушатель нажатий для всех элементов списка
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime:Crime){
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
        }

        override fun onClick(v: View) {
            Toast.makeText(context,"${crime.title} passed!" ,Toast.LENGTH_SHORT).show()
        }
    }

    //Холдер для серьёзных преступлений
    private inner class SeriousCrimeHolder(view: View):CrimeHolder(view),View.OnClickListener{
        private val policeButton:Button = itemView.findViewById(R.id.call_police)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime){
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()

            //При нажатие на кпноку преступления выводится это сообщение
            policeButton.setOnClickListener{
                Toast.makeText(context,"Calling the Police",Toast.LENGTH_SHORT).show()
            }
        }
        //При нажатие на само преступление выводится это сообщение
        override fun onClick(v: View) {
            Toast.makeText(context,"${crime.title} passed!" ,Toast.LENGTH_SHORT).show()
        }
    }


    //RecyclerView не создает ViewHolder сам по себе, вместо этого используется адаптер
    private inner class CrimeAdapter(var crimes:List<Crime>):RecyclerView.Adapter<CrimeHolder>(){

        //создание ViewHolder по запросу
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {

            //Если преступлние обыденное то надуваем старый макет и оборачиваем в сооветствующий холдер
            return when(viewType){
                0 -> {
                    val view = layoutInflater.inflate(R.layout.list_item_crime,parent,false)
                    NormalCrimeHolder(view)
                }
                else -> {
                    val view = layoutInflater.inflate(R.layout.serious_list_item_crime,parent,false)
                    SeriousCrimeHolder(view)
                }

            }
        }

        override fun getItemCount() = crimes.size

        //связывание ViewHolder с данными из модельного слоя
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            when (holder) {
                is NormalCrimeHolder -> holder.bind(crime)
                is SeriousCrimeHolder -> holder.bind(crime)
                else -> throw IllegalArgumentException()
            }
        }

        //Переопределяется в самом адаптере
        override fun getItemViewType(position: Int): Int {
            val crime = crimes[position]
            return when (crime.requiresPolice) {
                true -> 1
                else -> 0
            }
        }
    }


    companion object{
        fun newInstance():CrimeListFragment{
            return CrimeListFragment()
        }
    }
}