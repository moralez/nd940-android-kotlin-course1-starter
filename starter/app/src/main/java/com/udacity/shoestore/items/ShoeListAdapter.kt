package com.udacity.shoestore.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(
    val shoes: LiveData<MutableList<Shoe>>,
    private val selectionListener: ShoeSelectedListener
): RecyclerView.Adapter<ShoeListAdapter.ShoeItemView>() {

    private lateinit var binding: ShoeItemBinding

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeItemView {
        binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeItemView(binding.root)
    }

    override fun onBindViewHolder(holder: ShoeItemView, position: Int) {
        val shoe = shoes.value.orEmpty()[position]
        holder.company.text = shoe.name
        holder.name.text = shoe.company

        binding.shoe = shoe
        binding.selectionListener = selectionListener
    }

    override fun getItemCount(): Int = shoes.value.orEmpty().size

    override fun getItemId(position: Int): Long = position.toLong()

    inner class ShoeItemView(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val company: TextView = itemView.findViewById(R.id.shoeCompany)
        val name: TextView = itemView.findViewById(R.id.shoeName)
    }
}