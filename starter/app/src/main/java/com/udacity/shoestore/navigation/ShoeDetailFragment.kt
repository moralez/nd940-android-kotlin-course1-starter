package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber
import java.util.*

class ShoeDetailFragment: Fragment() {
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val args = ShoeDetailFragmentArgs.fromBundle(requireArguments())
        Timber.tag("jmo").d("Args: $args")
        Timber.tag("jmo").d("Args: ${args.shoe}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(
            ShoeDetailMenuProvider(),
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    private fun save() {
        viewModel.addShoe(
            Shoe(
                name = "Air Zoom",
                size = 9.5,
                company = "Nike",
                description = "Sick stuff",
                images = emptyList(),
                dateAdded = Date()
            )
        )
    }

    inner class ShoeDetailMenuProvider : MenuProvider {
        override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.shoe_details_menu, menu)
        }

        override fun onMenuItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_save -> {
                    save()
                    val action = ShoeDetailFragmentDirections.saveShoeDetails()
//                findNavController().navigate(action)
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }
}