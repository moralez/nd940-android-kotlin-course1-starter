package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment: Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        val args = WelcomeFragmentArgs.fromBundle(requireArguments())

        binding.welcomeTitle.text = when {
            args.newAccount -> getString(R.string.initial_welcome, args.email)
            else -> getString(R.string.return_welcome, args.email)
        }

        binding.enterButton.setOnClickListener {
            loadInstructions()
        }

        return binding.root
    }

    private fun loadInstructions() {
        val action = WelcomeFragmentDirections.loadInstructions()
        findNavController().navigate(action)
    }
}