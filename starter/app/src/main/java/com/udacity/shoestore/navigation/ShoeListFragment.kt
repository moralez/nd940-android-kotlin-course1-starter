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
import com.udacity.shoestore.databinding.FragmentShoeListScrollViewBinding
import com.udacity.shoestore.items.ShoeListAdapter
import com.udacity.shoestore.items.ShoeListViewAdapter
import com.udacity.shoestore.items.ShoeSelectedListener
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber

class ShoeListFragment: Fragment() {
    private val useRecyclerView = false

    private var _recyclerViewBinding: FragmentShoeListBinding? = null
    private val recyclerViewBinding get() = _recyclerViewBinding!!

    private var _listViewBinding: FragmentShoeListScrollViewBinding? = null
    private val listViewBinding get() = _listViewBinding!!

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var shoeListAdapter: ShoeListAdapter
    private lateinit var shoeListViewAdapter: ShoeListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (useRecyclerView) {
            _recyclerViewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_list, container,false
            )
        } else {
            _listViewBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_list_scroll_view,
                container,
                false
            )
        }

        if (useRecyclerView) { recyclerViewBinding.fab } else { listViewBinding.fab }.apply {
            setOnClickListener {
                loadShoeDetails()
            }
        }

        if (useRecyclerView) {
            shoeListAdapter = ShoeListAdapter(viewModel.shoes, object : ShoeSelectedListener {
                override fun shoeSelected(shoe: Shoe) {
                    Timber.tag("jmo").d("Selected Shoe: ${shoe.name} ${shoe.dateAdded}")
                    loadShoeDetails(shoe)
                }
            })
            linearLayoutManager = LinearLayoutManager(requireContext())
            recyclerViewBinding.shoeListView.layoutManager = linearLayoutManager
            recyclerViewBinding.shoeListView.adapter = shoeListAdapter
        } else {
            shoeListViewAdapter = ShoeListViewAdapter(requireContext(), viewModel.shoes)
            listViewBinding.shoeListView.adapter = shoeListViewAdapter
        }

        viewModel.shoes.observe(viewLifecycleOwner) {
            if (useRecyclerView) {
                shoeListAdapter.notifyDataSetChanged()
            } else {
                shoeListViewAdapter.notifyDataSetChanged()
            }
        }

        return if (useRecyclerView) recyclerViewBinding.root else listViewBinding.root
    }

    private fun loadShoeDetails(shoe: Shoe? = null) {
        val action = ShoeListFragmentDirections.loadShoeDetails()
        action.shoe = shoe
        findNavController().navigate(action)
    }
}