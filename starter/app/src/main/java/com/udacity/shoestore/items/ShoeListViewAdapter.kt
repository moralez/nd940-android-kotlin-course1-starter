package com.udacity.shoestore.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.lifecycle.LiveData
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListViewAdapter(
    private val context: Context,
    private val shoes: LiveData<MutableList<Shoe>>
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = shoes.value?.size ?: 0

    override fun getItem(position: Int): Shoe = shoes.value?.get(position) ?: Shoe.emptyShoe()

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ShoeItemBinding.inflate(inflater, parent, false)

        val shoe = shoes.value.orEmpty()[position]
        binding.shoeCompany.text = shoe.name
        binding.shoeName.text = shoe.company

        return binding.root
    }
}