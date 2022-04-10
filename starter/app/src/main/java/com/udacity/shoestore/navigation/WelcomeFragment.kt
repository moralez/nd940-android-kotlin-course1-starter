package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        val welcomeText = if (args.newAccount) {
            "Welcome ${args.email}"
        } else {
            "Welcome back ${args.email}"
        }
        binding.introTitle.text = welcomeText

        return binding.root
    }
}