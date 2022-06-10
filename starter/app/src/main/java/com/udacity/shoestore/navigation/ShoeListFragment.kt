package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.items.ShoeListAdapter
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeListFragment: Fragment() {
    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ShoeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.fab.setOnClickListener { _ ->
            viewModel.addShoe(
                Shoe(
                    name = "Air Zoom",
                    size = 9.5,
                    company = "Nike",
                    description = "Sick stuff",
                    images = emptyList()
                )
            )
        }

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        viewModel.shoes.observe(viewLifecycleOwner) {
            adapter.notifyItemChanged(it.size-1)
        }

        linearLayoutManager = LinearLayoutManager(requireContext())
        adapter = ShoeListAdapter(viewModel.shoes)
        binding.shoeListView.layoutManager = linearLayoutManager
        binding.shoeListView.adapter = adapter

        return binding.root
    }
}