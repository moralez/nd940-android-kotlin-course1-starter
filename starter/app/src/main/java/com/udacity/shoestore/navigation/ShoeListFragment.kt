package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.items.ShoeListAdapter
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber

class ShoeListFragment: Fragment() {
    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ShoeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.fab.setOnClickListener { _ ->
//            viewModel.addShoe(
//                Shoe(
//                    name = "Air Zoom",
//                    size = 9.5,
//                    company = "Nike",
//                    description = "Sick stuff",
//                    images = emptyList()
//                )
//            )
            loadShoeDetails()
        }

        viewModel.shoes.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }

        linearLayoutManager = LinearLayoutManager(requireContext())
        adapter = ShoeListAdapter(viewModel.shoes) {
            Timber.tag("jmo").d("Selected Shoe: ${it.name} ${it.dateAdded}")
            loadShoeDetails(it)
        }
        binding.shoeListView.layoutManager = linearLayoutManager
        binding.shoeListView.adapter = adapter

        return binding.root
    }

    private fun loadShoeDetails(shoe: Shoe? = null) {
        val action = ShoeListFragmentDirections.loadShoeDetails()
        action.shoe = shoe
        findNavController().navigate(action)
    }
}