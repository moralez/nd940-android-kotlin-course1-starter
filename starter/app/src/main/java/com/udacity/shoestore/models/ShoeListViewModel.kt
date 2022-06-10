package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel: ViewModel() {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        val currList = _shoes.value.orEmpty().toMutableList()
        currList.add(shoe)
        _shoes.value = currList
    }
}