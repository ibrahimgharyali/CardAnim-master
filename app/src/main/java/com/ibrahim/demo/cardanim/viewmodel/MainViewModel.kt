package com.ibrahim.demo.cardanim.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.demo.cardanim.model.Person

class MainViewModel : ViewModel() {

//    constructor(application: Application):super(application)

    var personList = MutableLiveData<List<Person>>()

    fun loadpersonList(list : List<Person>){
        personList.value = list
    }
}