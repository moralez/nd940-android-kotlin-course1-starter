package com.udacity.shoestore.items

import com.udacity.shoestore.models.Shoe

interface ShoeSelectedListener {
    fun shoeSelected(shoe: Shoe)
}