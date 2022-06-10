package com.udacity.shoestore.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(
    private val shoes: LiveData<MutableList<Shoe>>
): RecyclerView.Adapter<ShoeListAdapter.ShoeItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeItemView {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.shoe_item, parent, false)
        return ShoeItemView(view)
    }

    override fun onBindViewHolder(holder: ShoeItemView, position: Int) {
        holder.name.text = shoes.value.orEmpty()[position].name
    }

    override fun getItemCount(): Int = shoes.value.orEmpty().size

    class ShoeItemView(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.shoeName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Snackbar.make(view, "Selected Shoe", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show()
        }
    }
}